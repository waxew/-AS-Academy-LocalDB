#!/usr/bin/env python3
import json
import sys
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parents[1]
ROOT = REPO_ROOT / "academy-main-course" / "courses" / "localdb" / "course"


def load(path: Path):
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def load_objects(folder: str):
    result = []
    for path in sorted((ROOT / folder).glob("*.json")):
        data = load(path)
        if isinstance(data, list):
            result.extend(data)
        else:
            result.append(data)
    return result


def require(condition: bool, message: str, errors: list[str]):
    if not condition:
        errors.append(message)


def unique_ids(items, label, errors):
    seen = set()
    for item in items:
        item_id = item.get("id")
        require(bool(item_id), f"{label}: missing id", errors)
        if item_id:
            require(item_id not in seen, f"{label}: duplicate id {item_id}", errors)
            seen.add(item_id)
    return seen


def main():
    errors = []
    if not (ROOT / "manifest.json").is_file():
        print(f"Course validation failed: MainCourse package not found at {ROOT}")
        return 1

    manifest = load(ROOT / "manifest.json")
    levels = load(ROOT / "levels.json")
    chapters = load(ROOT / "chapters.json")
    lessons = load_objects("lessons")
    exercises = load_objects("exercises")
    quizzes = load_objects("quizzes")
    projects = load_objects("projects")
    glossary = load_objects("glossary")

    require(manifest.get("courseId") == "localdb", "manifest.courseId must be localdb", errors)
    require(bool(manifest.get("version")), "manifest.version is required", errors)

    level_ids = unique_ids(levels, "level", errors)
    chapter_ids = unique_ids(chapters, "chapter", errors)
    lesson_ids = unique_ids(lessons, "lesson", errors)
    unique_ids(exercises, "exercise", errors)
    unique_ids(quizzes, "quiz", errors)
    unique_ids(projects, "project", errors)

    for chapter in chapters:
        require(chapter.get("levelId") in level_ids, f"chapter {chapter.get('id')} references missing level {chapter.get('levelId')}", errors)
    for lesson in lessons:
        require(lesson.get("chapterId") in chapter_ids, f"lesson {lesson.get('id')} references missing chapter {lesson.get('chapterId')}", errors)
        require(bool(lesson.get("blocks")), f"lesson {lesson.get('id')} has no content blocks", errors)
    for exercise in exercises:
        require(exercise.get("lessonId") in lesson_ids, f"exercise {exercise.get('id')} references missing lesson {exercise.get('lessonId')}", errors)
    for quiz in quizzes:
        questions = quiz.get("questions", [])
        require(len(questions) > 0, f"quiz {quiz.get('id')} has no questions", errors)
        for question in questions:
            answers = question.get("answers", [])
            require(any(a.get("isCorrect") for a in answers), f"quiz {quiz.get('id')} question {question.get('id')} has no correct answer", errors)

    require(len(glossary) >= 10, "glossary should contain at least 10 entries", errors)
    require(len(projects) >= 5, "course should contain at least 5 projects", errors)

    if errors:
        print("Course validation failed:")
        for error in errors:
            print(f"- {error}")
        return 1

    print(
        "MainCourse validation passed: "
        f"{len(levels)} levels, {len(chapters)} chapters, {len(lessons)} lessons, "
        f"{len(exercises)} exercises, {len(quizzes)} quizzes, {len(projects)} projects, {len(glossary)} glossary entries."
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())

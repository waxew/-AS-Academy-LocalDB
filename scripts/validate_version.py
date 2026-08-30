#!/usr/bin/env python3
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
app_gradle = (ROOT / "app" / "build.gradle.kts").read_text(encoding="utf-8")
manifest = json.loads((ROOT / "course" / "localdb" / "manifest.json").read_text(encoding="utf-8"))

version_name_match = re.search(r'versionName\s*=\s*"([^"]+)"', app_gradle)
version_code_match = re.search(r'versionCode\s*=\s*(\d+)', app_gradle)

errors = []
if not version_name_match:
    errors.append("versionName not found in app/build.gradle.kts")
if not version_code_match:
    errors.append("versionCode not found in app/build.gradle.kts")

if version_name_match:
    app_version = version_name_match.group(1)
    course_version = str(manifest.get("version", ""))
    if app_version != course_version:
        errors.append(f"app versionName {app_version} != course manifest version {course_version}")

if version_code_match:
    version_code = int(version_code_match.group(1))
    if version_code < 1:
        errors.append("versionCode must be >= 1")

if errors:
    print("Version validation failed:")
    for error in errors:
        print(f"- {error}")
    sys.exit(1)

print(f"Version validation passed: versionName={version_name_match.group(1)}, versionCode={version_code_match.group(1)}")

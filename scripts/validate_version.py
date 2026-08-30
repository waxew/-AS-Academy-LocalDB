#!/usr/bin/env python3
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
app_gradle = (ROOT / "app" / "build.gradle.kts").read_text(encoding="utf-8")
manifest = json.loads((ROOT / "course" / "localdb" / "manifest.json").read_text(encoding="utf-8"))
baseline = json.loads((ROOT / "release-baseline.json").read_text(encoding="utf-8"))

version_name_match = re.search(r'versionName\s*=\s*"([^"]+)"', app_gradle)
version_code_match = re.search(r'versionCode\s*=\s*(\d+)', app_gradle)
application_id_match = re.search(r'applicationId\s*=\s*"([^"]+)"', app_gradle)

errors = []
if not version_name_match:
    errors.append("versionName not found in app/build.gradle.kts")
if not version_code_match:
    errors.append("versionCode not found in app/build.gradle.kts")
if not application_id_match:
    errors.append("applicationId not found in app/build.gradle.kts")

if version_name_match:
    app_version = version_name_match.group(1)
    course_version = str(manifest.get("version", ""))
    if app_version != course_version:
        errors.append(f"app versionName {app_version} != course manifest version {course_version}")
    if app_version != str(baseline.get("nextVersionName", "")):
        errors.append(f"app versionName {app_version} != release baseline nextVersionName {baseline.get('nextVersionName')}")

if version_code_match:
    version_code = int(version_code_match.group(1))
    last_published = int(baseline.get("lastPublishedVersionCode", 0))
    expected_next = int(baseline.get("nextVersionCode", 0))
    if version_code <= last_published:
        errors.append(f"versionCode {version_code} must be greater than published baseline {last_published}")
    if version_code != expected_next:
        errors.append(f"versionCode {version_code} != release baseline nextVersionCode {expected_next}")

if application_id_match:
    application_id = application_id_match.group(1)
    if application_id != baseline.get("applicationId"):
        errors.append(f"applicationId {application_id} != release baseline {baseline.get('applicationId')}")

if errors:
    print("Version validation failed:")
    for error in errors:
        print(f"- {error}")
    sys.exit(1)

print(
    "Version validation passed: "
    f"applicationId={application_id_match.group(1)}, "
    f"versionName={version_name_match.group(1)}, "
    f"versionCode={version_code_match.group(1)}"
)

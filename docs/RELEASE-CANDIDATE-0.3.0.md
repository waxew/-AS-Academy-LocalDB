# AS Academy LocalDB 0.3.0 — Release Candidate

## وضعیت

نسخه 0.3.0 از نظر Course Package، نسخه‌بندی، Debug build، Release variant build و CI آماده Release Candidate است.

## شناسه و نسخه

- applicationId: `com.asdevelopers.academy.localdb`
- versionCode: `3`
- versionName: `0.3.0`
- Course Package version: `0.3.0`
- Academy database: `as_academy.db`
- Academy database schema version: `1`

## محتوای تاییدشده توسط CI

- 6 سطح آموزشی
- 16 فصل
- 33 درس
- 11 تمرین
- 10 آزمون
- 7 پروژه عملی
- 26 مدخل واژه‌نامه

## Gateهای فعال

- Course package validation
- App/Course version consistency
- Monotonic versionCode validation
- applicationId stability check
- Debug APK build
- Release variant build
- Debug artifact upload
- Unsigned Release artifact upload
- Signed release workflow با GitHub Secrets
- SHA-256 و apksigner verification در Signed Release workflow

## وضعیت Persistence

Core پایدار فعلی `AcademyDatabase` با schema version 1 اجرا می‌شود و destructive migration به‌صورت پیش‌فرض فعال نیست. قرارداد حفظ Progress و Upgrade در `docs/PROGRESS-PERSISTENCE-CONTRACT.md` و تست دستی N→N+1 در `docs/UPGRADE-QA.md` ثبت شده است.

## مواردی که قبل از Production Release باید خارج از Build عادی انجام شوند

1. GitHub Secrets مربوط به signing واقعی تنظیم شوند.
2. Signed Release workflow اجرا شود.
3. certificate خروجی با نسخه منتشرشده قبلی مقایسه شود.
4. نسخه 0.2.0 روی Emulator/Device نصب و داده تست ساخته شود.
5. نسخه Signed 0.3.0 بدون Uninstall روی آن نصب شود.
6. Progress، Bookmark، Note، Quiz، Exercise و Project دوباره بررسی شوند.
7. Back navigation مسیرهای اصلی تست شود.

تا قبل از پاس شدن موارد بالا، 0.3.0 باید Release Candidate تلقی شود، نه Production Release.

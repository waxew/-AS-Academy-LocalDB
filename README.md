# AS Academy LocalDB

آموزش جامع دیتابیس داخلی و آفلاین اندروید از سطح مبانی تا تخصصی.

این ریپو فقط کد و محتوای اختصاصی دوره LocalDB را نگه می‌دارد. Navigation، Design System، Progress، Quiz، Exercise، Search، Bookmark، Settings، Drawer/Profile، Content Engine و قرارداد Course Package از `AS-Academy-Core` استفاده می‌شوند.

## مسیر آموزشی
- SharedPreferences و DataStore
- SQL و SQLite
- Room Database
- Entity، DAO و Repository
- CRUD
- Search / Sort / Filter
- Relationها
- Migration و Versioning
- Backup / Restore / Import / Export
- Encryption و Security
- Testing / Debugging / Performance
- Offline-first و Sync
- پروژه‌های عملی واقعی

## معماری
- `app`: پوسته سبک اپ Android
- `academy-course`: تنظیمات Runtime اختصاصی LocalDB
- `course/localdb`: درس‌ها، تمرین‌ها، آزمون‌ها و پروژه‌ها
- `academy-core`: هسته مشترک AS Academy به صورت Git submodule

نسخه اولیه: `0.1.0`

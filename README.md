# AS Academy LocalDB

اپ آموزشی جامع دیتابیس داخلی و آفلاین اندروید از مبانی تا معماری‌های تخصصی Offline-first.

این ریپو فقط کد و محتوای اختصاصی دوره LocalDB را نگه می‌دارد. Navigation، Design System، دیتابیس پیشرفت، Search، Bookmark، Settings، Drawer و Content Engine از `AS-Academy-Core` می‌آیند.

## مسیر آموزشی
DataStore → SQL → SQLite → Room → CRUD → Relation → Migration → Backup/Restore → Security → Performance/Testing → Offline-first → پروژه‌های واقعی

## وضعیت نسخه 0.2.0
- ۶ سطح آموزشی
- ۱۶ فصل
- ۱۳ درس واقعی پایه
- ۴ تمرین اصلی
- ۵ آزمون سطحی
- ۴ پروژه عملی شامل پروژه نهایی حسابداری آفلاین
- GitHub Actions برای تولید Debug APK
- Core به commit سازگار و Build-tested متصل شده است

## معماری
- `app`: پوسته سبک Android
- `academy-course`: تنظیمات Runtime اختصاصی LocalDB
- `course/localdb`: Course Package و محتوای آموزشی
- `academy-core`: هسته مشترک AS Academy به صورت Git submodule

Develop by AS Team Group

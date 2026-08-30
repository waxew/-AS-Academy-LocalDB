# LocalDB Progress Persistence Contract

این قرارداد مشخص می‌کند چه داده‌هایی در ارتقای نسخه AS Academy LocalDB نباید از بین بروند.

## داده‌های پایدار

- وضعیت تکمیل Lesson برای هر Course و Lesson ID
- Bookmarkهای کاربر
- Noteهای کاربر
- Quiz History شامل امتیاز، پاسخ صحیح/غلط، weak tags و زمان تکمیل
- Exercise completion و Exercise Draft
- Project completion، milestone progress و Project Draft
- تنظیمات محلی کاربر که توسط Core ذخیره می‌شوند

## قواعد شناسه

شناسه‌های منتشرشده Course، Chapter، Lesson، Quiz، Exercise و Project بعد از Release نباید بدون Migration تغییر کنند. تغییر ID از دید Persistence معادل ساخت یک محتوای جدید است و می‌تواند اتصال Progress قبلی را قطع کند.

## قواعد دیتابیس

- نام دیتابیس منتشرشده بدون برنامه Migration تغییر نکند.
- افزایش schema version باید Migration صریح داشته باشد.
- destructive migration برای Release عادی ممنوع است.
- Migration باید روی داده واقعی نسخه قبلی تست شود.
- جدول‌ها و کلیدهای Progress باید Course-aware باقی بمانند تا دوره‌ها با یکدیگر تداخل نداشته باشند.

## تست Upgrade اجباری

1. روی نسخه N داده نمونه برای Lesson، Bookmark، Note، Quiz، Exercise و Project بسازید.
2. Snapshot مقادیر مورد انتظار را ثبت کنید.
3. نسخه N+1 را با همان applicationId و signing certificate روی نسخه N نصب کنید.
4. تمام رکوردهای Snapshot را بخوانید و با مقدار قبلی مقایسه کنید.
5. یک رکورد جدید در N+1 ایجاد کنید تا write path بعد از Migration نیز تأیید شود.
6. اپ را Force Stop و دوباره اجرا کنید و Persistence را دوباره بررسی کنید.

## Release Gate

هر نسخه‌ای که داده نسخه قبلی را بدون Migration مستند از دست بدهد، Release-compatible محسوب نمی‌شود و نباید به‌عنوان آپدیت عمومی منتشر شود.

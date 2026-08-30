# LocalDB Upgrade QA Contract

این سند معیار اجباری انتشار نسخه‌های جدید AS Academy LocalDB است.

## هدف

نسخه جدید باید روی نسخه قبلی نصب شود و Progress، Bookmark، Note، Quiz History، Exercise Draft و Project Progress کاربر حفظ شوند.

## سناریوی Upgrade

1. نسخه قبلی APK را نصب کنید.
2. حداقل دو Lesson را باز کنید و یکی را Complete کنید.
3. یک Bookmark و یک Note بسازید.
4. یک Quiz را کامل کنید.
5. یک Exercise Draft ذخیره کنید.
6. حداقل یک Milestone پروژه را Complete کنید.
7. اپ را Force Stop و دوباره باز کنید و وجود داده‌ها را تأیید کنید.
8. APK نسخه جدید را بدون Uninstall روی همان applicationId نصب کنید.
9. اپ را باز کنید و تمام داده‌های مرحله‌های 2 تا 6 را دوباره بررسی کنید.
10. Back navigation را از Lesson، Quiz، Exercise، Project، Settings و About بررسی کنید.

## معیار قبولی

- applicationId تغییر نکرده باشد.
- versionCode نسخه جدید بزرگ‌تر از نسخه منتشرشده قبلی باشد.
- Signing key برای Release ثابت بماند.
- هیچ destructive migration برای داده کاربر استفاده نشود مگر با تصمیم صریح و مستند.
- Progress و داده‌های آموزشی بعد از Upgrade باقی بمانند.
- Back از صفحات داخلی به مقصد قبلی برگردد و باعث خروج ناگهانی نشود.
- Course Package validation و Android Build در CI سبز باشند.

## Release Gate

تا زمانی که سناریوی Upgrade روی Emulator یا Device واقعی پاس نشده، نسخه Release نهایی تلقی نمی‌شود.

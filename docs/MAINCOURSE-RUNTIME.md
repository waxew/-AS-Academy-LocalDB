# LocalDB MainCourse Runtime Contract

از این مرحله، منبع Runtime محتوای آموزشی LocalDB مسیر زیر است:

`academy-main-course/courses/localdb/course`

اپ LocalDB هنگام Build همین Course Package را به مسیر assets مورد انتظار Core یعنی `course/localdb` منتقل می‌کند. بنابراین Loader فعلی Core بدون fork شدن محتوا، درس‌ها، تمرین‌ها، Quizها، پروژه‌ها و Glossary را از MainCourse داخل APK دریافت می‌کند.

پوشه قدیمی `course/localdb` در ریپوی LocalDB فقط Snapshot مهاجرتی است و نباید محل توسعه محتوای جدید باشد.

قواعد:

1. تغییر محتوای آموزشی ابتدا در `AS-Academy-MainCourse/courses/localdb/course` انجام می‌شود.
2. CI باید Course Package مرکزی را validate کند.
3. APK نباید برای محتوای Runtime به Snapshot محلی وابسته باشد.
4. مسیر asset داخل APK همچنان `course/localdb` می‌ماند تا قرارداد Loader و Progress IDها نشکند.
5. حذف Snapshot محلی فقط بعد از چند Build/Upgrade موفق انجام می‌شود.

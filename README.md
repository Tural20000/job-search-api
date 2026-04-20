# Job Search API 🚀

Bu layihə, iş elanlarının idarə edilməsi üçün nəzərdə tutulmuş, Spring Boot və PostgreSQL əsaslı bir Backend tətbiqidir. Layihə tamamilə Dockerize edilib və mikroservis arxitekturasına uyğun şəkildə qurulub.

## ✨ Xüsusiyyətlər
- **Spring Boot 3.4.2** və **Java 21** istifadə olunub.
- **Spring Data JPA** vasitəsilə verilənlər bazası ilə əlaqə.
- **Spring Security** və **JWT** (JSON Web Token) ilə təhlükəsizlik.
- **PostgreSQL 15** verilənlər bazası.
- **Docker & Docker Compose** ilə asan quraşdırma.
- Layihə başladıqda avtomatik olaraq nümunə məlumatların (jobs) bazaya yerləşdirilməsi.

## 🛠 Texnologiyalar
- **Backend:** Java, Spring Boot, Hibernate, Spring Security.
- **Database:** PostgreSQL.
- **DevOps:** Docker, Docker Compose.

## 🚀 Layihəni İşə Salmaq

Layihəni öz kompyuterinizdə işə salmaq üçün sadəcə aşağıdakı addımları izləyin:

1. **Repozitoriyanı klonlayın:**
   ```bash
   git clone [https://github.com/Tural20000/job-search-api.git](https://github.com/Tural20000/job-search-api.git)
Docker vasitəsilə ayağa qaldırın:
Terminalda layihənin kök qovluğuna keçid edərək bu əmri yazın:

Bash
docker-compose up --build
API giriş nöqtəsi:
Tətbiq hazır olduqdan sonra http://localhost:8019 ünvanında işləməyə başlayacaq.

📊 Verilənlər Bazası Struktur
Tətbiq işə düşərkən avtomatik olaraq aşağıdakı cədvəlləri yaradır:

jobs - İş elanları üçün.

users - İstifadəçi məlumatları üçün.

roles - İstifadəçi rolları (Admin, User) üçün.

user_roles - İstifadəçi və rolların əlaqəsi üçün.

Developed by Tural Abdullayev
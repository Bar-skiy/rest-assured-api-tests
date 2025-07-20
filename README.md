# Автоматизированные API-тесты на Rest Assured + Allure

Простой Java-проект, демонстрирующий автоматизированное тестирование REST API с использованием **Rest Assured**, **JUnit 5** и **Allure Report**.

---

##  Возможности

- Настройка базового URL в `BaseTest`
- Поддержка API-ключа через заголовок `x-api-key: reqres-free-v1`
- Автотесты для следующих сценариев:
  - `GET /users/2` — получение пользователя по ID
  - `POST /login` — успешная авторизация
  - `POST /login` (негативный) — логин без пароля (ожидаем 400)
  - `POST /users` — создание нового пользователя
  - `DELETE /users/2` — удаление пользователя

---

##  Как запустить тесты

Убедитесь, что у вас установлены:

- Java 11 или выше
- Maven
- Allure CLI (для отчёта)

Выполните команду:

```bash
mvn clean test "-Dfile.encoding=UTF-8"
```

> `-Dfile.encoding=UTF-8` необходим для корректного отображения кириллицы в Allure

---

##  Allure Report

После выполнения тестов появится папка `allure-results/`.

Генерация и автоматическое открытие отчёта:

```bash
allure serve allure-results
```

Если не работает `serve`, используйте вручную:

```bash
allure generate allure-results --clean -o allure-report
start allure-report/index.html
```

>  Не открывайте `index.html` напрямую через `file://` — отчёт не загрузится корректно

---

##  Используемые технологии

- Java 17+
- Maven
- Rest Assured 5.5.0
- JUnit 5.10.1
- Allure 2.28.1


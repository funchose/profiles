# Profiles <a name="about-project"></a>

> **Приложение Profiles** позволяет хранить данные и контактную информацию о пользователях, зарегистрированных в системе.
> Авторизация пользователей осуществляется по JWT

### Технологии <a name="built-with"></a>

  <ul>
    <li>Java 21</li>
    <li>Maven 3.9.2</li>
    <li>PostgreSQL 16.2</li>
  </ul>
  
## Структура проекта

В ветке [**master**](https://github.com/funchose/profiles/tree/master) находится версия проекта с реализацией CRUD модели для работы с полной и контактной информацией пользователей, а также [JSON](https://github.com/funchose/profiles/blob/master/profiles.postman_collection.json) для тестирования API данной версии

В ветке [**security**](https://github.com/funchose/profiles/tree/security) находится версия проекта с дополнительной реализацией возможности регистрации пользователей, их аутентификации и авторизации по JWT, а также [JSON](https://github.com/funchose/profiles/blob/security/profiles.postman_collection.json) для тестирования API данной версии


## Использование

Перед запуском приложения:
1. Пропишите корректный **URL** в файле `src/main/resources/application.properties`
  
2. Добавьте переменные окружения с логином `DB_USERNAME` и паролем `DB_PASSWORD` пользователя базы данных для подключения к БД
  
3. Добавьте в переменные окружения секрет для подписи токена `JWT_SECRET`, например: *asdfvSsdfavGdHT4df3g6gasdSFIUI9OIKLDQADOPIJLFrRsasvsvsNb309hkBG*


Приложение при запуске создаст пустую таблицу в базе данных, предварительно ее создавать не нужно

Тесты запускаются на встроенной базе данных H2, не требующей дополнительной настройки

## Авторы <a name="authors"></a>

 **Анна Поповa**

- GitHub: [funchose](https://github.com/funchose)
- Telegram @funch0se

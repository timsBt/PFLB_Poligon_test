# Чек-лист PFLB Test-API
---
### 1. Авторизация:

- Проверка на корректную авторизацию. Ввод данных email и password, клик кнопку Go. 
- Проверка на пустые поля пустые. 
- Проверка на некорректный пароль.  
- Проверка на некорректный логин.
- Проверка на некорректный логин и пароль.
---
### 2. Взаимодействие с пользователем:

- Проверка на создание нового пользователя. Ввод First Name, Last Name, Age, Money, выбор Sex. 
- Проверка отображения созданного пользователя в таблице Read all
- Проверка на отсутствие результата создания пользователя. Ввод некорректных данных.
- Проверка на удаление пользователя. Ввод корректного id.
- Проверка на удаление несуществующего пользователя. Ввод несуществующего id.
---
### 3. Взаимодействие с деньгами:
- Проверка на добавление денег пользователю. Ввод корректного id и суммы.
- Проверка на добавление денег несуществующему пользователю. Ввод некорректного id и суммы.
- Проверка на ввод некорректной суммы. Ввод корректного id и отрицательной суммы.
- Проверка на выдачу кредита пользователю. Ввод корректного id и корректной суммы.
- Проверка на выдачу кредита несуществующему пользователю. Ввод некорректного id и суммы.
- Проверка на выдачу кредита с некорректной суммой. Ввод корректного id и отрицательной суммы.
---
### 4. Взаимодействие с автомобилями:
- Проверка на создание автомобиля. Ввод корректных данных Engine Type, Mark, Model, Price.
- Проверка на отсутствия результата создания автомобиля. Ввод некорректных данных.
- Проверка на удаление автомобиля. Ввод корректного id.
- Проверка на удаление несуществующего автомобиля. Ввод несуществующего id.
- Проверка на получение списка машин конкретного пользователя по id. Ввод корректного id пользователя.
- Проверка на отсутствия получение списка машин конкретного пользователя по id. Ввод некорректного id пользователя.
---
### 5. Взаимодействие пользователя с автомобилем:
- Проверка на покупку свободного автомобиля. Ввод корректного id пользователя и автомобиля, выбор покупки.
- Проверка на отсутствие результата покупки автомобиля. Ввод некорректного id пользователя/автомобиля.
- Проверка на продажу автомобиля. Ввод корректного id пользователя и автомобиля, выбор продажи.
- Проверка на покупку автомобиля у другого пользователя. Ввод корректного id пользователя и id автомобиля другого пользователя, выбор покупки.
- Проверка на покупку автомобиля при недостаточном количестве средств на счету пользователя.
---
### 6. Взаимодействие с домами:


---
### 7. Взаимодействие пользователя с домами:
- Проверка заселения пользователя в дом. Ввод корректного id пользователя и дома, выбор заселения.
- Проверка выселения пользователя из дома. Ввод корректного id пользователя и дома, выбор выселения.
- Проверка заселения пользователя с пустым полем пользователь. Ввод некорректного id пользователя и id корректного дома, выбор заселения
- Проверка заселения пользователя с пустым полем дом. Ввод корректного id пользователя и id некорректного дома, выбор заселения.
- Проверка выселения пользователя с пустым полем пользователь. Ввод некорректного id пользователя и id корректного дома, выбор выселения
- Проверка выселения пользователя с пустым полем дом.Ввод корректного id пользователя и id некорректного дома, выбор выселения.
---
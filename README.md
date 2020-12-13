# test
1. Програма створена і запускалась у середовищі Eclipse. (server - Tomcat v9.0)
2. У архів з проектом додаю файл експорту бази даних(MySql).
3. Перевірка:
	а. У екліпсі запускаю проект на сервері (правий клік по проекті ---> Run As ---> Run on Server))
	б. у браузері відкривається сторінка index.jsp.
	в. щоб намалювати кімнату, раніше збережену в БД, потрібно ввести в текстове поле 'Name' її ім'я і натиснути DRAW.
	г. для додавання нової кімнати потрібно натиснути add room, після цього ввести у текстові поля ім'я, максимально допустиму площу та кількість точок, з яких буде побудована кімната, та натиснути на посилання 'add points'.
	д. у полях, які щойно з'явились, ввести координати точок та натиснути кнопку SaveRoom.
	е. після натискання програма перевіряє введені дані на різноманітні помилки(рух точок проти стрілки годинника, обмежена площа, діагональні стіни, перетин стін і тд...).
	є. я використовував формулу Гаусса для обчислення площі.
	ж. якщо дані не пройшли перевірку, то причина помилки буде виведена у консолі Eclipse( так як наведено у scr1.png)
	з. anticlockwise - це для прикладу помилка зв'язана з тим, що точки рухаються не за год. стрілкою.
	и. рисунок кімнати відображається за допомогою java.Canvas, а не в html(html <canvas> конфігурується за допомогою JS і я не зміг отримувати точки з бд за допомогою JS, тому використав JAVA)
	і. приклад рисунку можете побачити на scr2.png
Єдине, що я не вирішив, є те, що кімнати малюються зеркально перевернуті. не розумію чому, але нічого не зміг з цим зробити.
приклади для тестування : 
1.
	запускаємо програму і вводимо в Name 'test2'(ця кімната вже додана в БД)
	натискаємо draw і відкривається вікно з рисунком кімнати( але перевернуте!!).
2.	
	натискаємо add room	
	вводимо будь яке імя
	певну максимальну площу	
	к-сть точок
	натискаємо add points	
	вводимо координати
	натискаємо saveroom
	(після цього можна за допомогою draw відразу відобразити кімнату, звісно, якщо вона пройшла перевірки і збереглась в бд)
3. приклади координат для пункту 2 :
	1. (2,2) (2,5) (7,5) (7,2)- кімната валідна і буде збережена, якщо макс. допустима площа буде встановена не менше ніж 7.5(за формулою Гаусса площа такої фігури = 7.5)
	2. (1,1) (2,1) (2,2) (1,2) - anticlockwise)).
	3. (1,1) (1,3) (3,3) (3,4) (2,4) (2,2) (0,2) (0,1) - intersected lines
	4. для перевірки на обмежену площу можете використати пункт 1 і встановити макс. доп. площу наприклад = 3.
Також можете спробувати будь-які інші координати і перевіряти працездатність програми.

Розумію, що цей проект можна вдосконалити, наприклад, додати логування помилок і тд, зробити красивий дизайн, але я ще не опанував повністю можливості всіх мов програмування, використаних у цьому проекті)).

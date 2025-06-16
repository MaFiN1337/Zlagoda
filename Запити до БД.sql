-- 5. Отримати інформацію про усіх працівників, відсортованих за прізвищем        !!!   ГОТОВО !!!
SELECT * FROM Employee
ORDER BY empl_surname;

-- 6. Отримати інформацію про усіх працівників, що займають посаду касира, відсортованих за прізвищем  !!!   ГОТОВО !!!
SELECT * FROM Employee
WHERE empl_role = 'касир'
ORDER BY empl_surname;

-- 7. Отримати інформацію про усіх постійних клієнтів, відсортованих за прізвищем   !!!   ГОТОВО !!!
SELECT * FROM Customer_Card
ORDER BY cust_surname;

-- 8. Отримати інформацію про усі категорії, відсортовані за назвою                 !!!   ГОТОВО !!!
SELECT * FROM Category
ORDER BY category_name;

-- 9. Отримати інформацію про усі товари, відсортовані за назвою                     !!!   ГОТОВО !!!
SELECT * FROM Product
ORDER BY product_name;

-- 10. Отримати інформацію про усі товари у магазині, відсортовані за кількістю
SELECT * FROM Store_Product
ORDER BY products_number;

-- 11. За прізвищем працівника знайти його телефон та адресу                         !!!   ГОТОВО !!!
SELECT empl_name, empl_surname, phone_number, city, street, zip_code
FROM Employee
WHERE empl_surname = 'Ваше_прізвище';

-- 12. Отримати інформацію про усіх постійних клієнтів, що мають карту клієнта із певним відсотком, посортованих за прізвищем
 --                                                                                           !!!   ГОТОВО !!!
SELECT * FROM Customer_Card
WHERE percent = 10  -- замінити на потрібний відсоток
ORDER BY cust_surname;

-- 13. Здійснити пошук усіх товарів, що належать певній категорії, відсортованих за назвою     !!!   ГОТОВО !!!
SELECT p.* FROM Product p
JOIN Category c ON p.category_number = c.category_number
WHERE c.category_name = 'Назва_категорії'
ORDER BY p.product_name;

-- 14. За UPC-товару знайти ціну продажу товару, кількість наявних одиниць товару, назву та характеристики товару
SELECT sp.selling_price, sp.products_number, p.product_name, p.characteristics
FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.UPC = 'UPC_код';

-- 15. Отримати інформацію про усі акційні товари, відсортовані за кількістю одиниць товару/за назвою
-- За кількістю:
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY sp.products_number;

-- За назвою:
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY p.product_name;

-- 16. Отримати інформацію про усі не акційні товари, відсортовані за кількістю одиниць товару/за назвою
-- За кількістю:
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL
ORDER BY sp.products_number;

-- За назвою:
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL
ORDER BY p.product_name;

-- 17. Отримати інформацію про усі чеки, створені певним касиром за певний період часу   !!!   ГОТОВО !!!
SELECT c.check_number, c.print_date, c.sum_total, c.vat,
       s.product_number, s.selling_price, p.product_name
FROM Check c
JOIN Sale s ON c.check_number = s.check_number
JOIN Store_Product sp ON s.UPC = sp.UPC
JOIN Product p ON sp.id_product = p.id_product
WHERE c.id_employee = 'ID_касира'
  AND c.print_date BETWEEN '2024-01-01' AND '2024-12-31'
ORDER BY c.print_date, c.check_number;

-- 18. Отримати інформацію про усі чеки, створені усіма касирами за певний період часу   !!!   ГОТОВО !!!
SELECT c.check_number, c.print_date, c.sum_total, c.vat, e.empl_surname,
       s.product_number, s.selling_price, p.product_name
FROM Check c
JOIN Employee e ON c.id_employee = e.id_employee
JOIN Sale s ON c.check_number = s.check_number
JOIN Store_Product sp ON s.UPC = sp.UPC
JOIN Product p ON sp.id_product = p.id_product
WHERE c.print_date BETWEEN '2024-01-01' AND '2024-12-31'
ORDER BY c.print_date, c.check_number;

-- 19. Визначити загальну суму проданих товарів з чеків, створених певним касиром за певний період часу  !!!   ГОТОВО !!!
SELECT SUM(c.sum_total) as total_sales
FROM Check c
WHERE c.id_employee = 'ID_касира'
  AND c.print_date BETWEEN '2024-01-01' AND '2024-12-31';

-- 20. Визначити загальну суму проданих товарів з чеків, створених усіма касирами за певний період часу   !!!   ГОТОВО !!!
SELECT SUM(c.sum_total) as total_sales
FROM Check c
WHERE c.print_date BETWEEN '2024-01-01' AND '2024-12-31';

-- 21. Визначити загальну кількість одиниць певного товару, проданого за певний період часу
SELECT SUM(s.product_number) as total_quantity
FROM Sale s
JOIN Check c ON s.check_number = c.check_number
JOIN Store_Product sp ON s.UPC = sp.UPC
JOIN Product p ON sp.id_product = p.id_product
WHERE p.product_name = 'Назва_товару'
  AND c.print_date BETWEEN '2024-01-01' AND '2024-12-31';

-- Додаткові запити з другого списку:

-- 1. Отримати інформацію про усі товари, відсортовані за назвою (дублікат запиту 9)    !!!   ГОТОВО !!!
SELECT * FROM Product
ORDER BY product_name;

-- 2. Отримати інформацію про усі товари у магазині, відсортовані за назвою
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
ORDER BY p.product_name;

-- 3. Отримати інформацію про усіх постійних клієнтів, відсортованих за прізвищем (дублікат запиту 7) !!!   ГОТОВО !!!
SELECT * FROM Customer_Card
ORDER BY cust_surname;

-- 4. Здійснити пошук товарів за назвою                 !!!   ГОТОВО !!!
SELECT * FROM Product
WHERE product_name LIKE '%назва%';

-- 5. Здійснити пошук товарів, що належать певній категорії, відсортованих за назвою (дублікат запиту 13)    !!!   ГОТОВО !!!
SELECT p.* FROM Product p
JOIN Category c ON p.category_number = c.category_number
WHERE c.category_name = 'Назва_категорії'
ORDER BY p.product_name;

-- 6. Здійснити пошук постійних клієнтів за прізвищем    !!!   ГОТОВО !!!
SELECT * FROM Customer_Card
WHERE cust_surname LIKE '%прізвище%';

-- 7. Додавання чеку (продаж товарів)
INSERT INTO Check (check_number, id_employee, card_number, print_date, sum_total, vat)
VALUES ('номер_чеку', 'ID_касира', 'номер_картки', NOW(), сума, ПДВ);

-- Додавання товарів до чеку
INSERT INTO Sale (UPC, check_number, product_number, selling_price)
VALUES ('UPC_товару', 'номер_чеку', кількість, ціна);

-- 8. Додавання/редагування інформації про постійних клієнтів
-- Додавання:
INSERT INTO Customer_Card (card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent)
VALUES ('номер_картки', 'прізвище', 'ім\'я', 'по-батькові', 'телефон', 'місто', 'вулиця', 'індекс', відсоток);

-- Редагування:
UPDATE Customer_Card
SET cust_surname = 'нове_прізвище',
    cust_name = 'нове_ім\'я',
    phone_number = 'новий_телефон'
WHERE card_number = 'номер_картки';

-- 9. Переглянути список усіх чеків, що створив касир за цей день       !!!   ГОТОВО !!!
SELECT * FROM Check
WHERE id_employee = 'ID_касира'
  AND DATE(print_date) = CURDATE()
ORDER BY print_date;

-- 10. Переглянути список усіх чеків, що створив касир за певний період часу    !!!   ГОТОВО !!!
SELECT * FROM Check
WHERE id_employee = 'ID_касира'
  AND print_date BETWEEN '2024-01-01' AND '2024-12-31'
ORDER BY print_date;

-- 11. За номером чеку вивести усю інформацію про даний чек                        !!!   ГОТОВО !!!
SELECT c.*, e.empl_surname, cc.cust_surname,
       s.product_number, s.selling_price, p.product_name
FROM Check c
LEFT JOIN Employee e ON c.id_employee = e.id_employee
LEFT JOIN Customer_Card cc ON c.card_number = cc.card_number
LEFT JOIN Sale s ON c.check_number = s.check_number
LEFT JOIN Store_Product sp ON s.UPC = sp.UPC
LEFT JOIN Product p ON sp.id_product = p.id_product
WHERE c.check_number = 'номер_чеку';

-- 12. Отримати інформацію про усі акційні товари (дублікат запиту 15)
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY sp.products_number;

-- 13. Отримати інформацію про усі не акційні товари (дублікат запиту 16)
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL
ORDER BY sp.products_number;

-- 14. За UPC-товару знайти ціну продажу товару, кількість наявних одиниць товару (дублікат запиту 14)
SELECT selling_price, products_number
FROM Store_Product
WHERE UPC = 'UPC_код';

-- 15. Можливість отримати усю інформацію про себе (для працівника)        !!!   ГОТОВО !!!
SELECT * FROM Employee
WHERE id_employee = 'ваш_ID';
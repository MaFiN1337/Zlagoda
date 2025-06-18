-- 10. Отримати інформацію про усі товари у магазині, відсортовані за кількістю      !!!   ГОТОВО !!!
SELECT * FROM Store_Product
ORDER BY products_number;

-- 14. За UPC-товару знайти ціну продажу товару, кількість наявних одиниць товару, назву та характеристики товару
           --                                                                                 !!!   ГОТОВО !!!
SELECT sp.selling_price, sp.products_number, p.product_name, p.characteristics
FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.UPC = 'UPC_код';

-- 15. Отримати інформацію про усі акційні товари, відсортовані за кількістю одиниць товару/за назвою
-- За кількістю:                                                                    !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY sp.products_number;

-- За назвою:                                                          !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY p.product_name;

-- 16. Отримати інформацію про усі не акційні товари, відсортовані за кількістю одиниць товару/за назвою
-- За кількістю:                                                       !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL
ORDER BY sp.products_number;

-- За назвою:                                                       !!!   ГОТОВО !!!
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

-- 21. Визначити загальну кількість одиниць певного товару, проданого за певний період часу              !!!   ГОТОВО !!!
SELECT SUM(s.product_number) as total_quantity
FROM Sale s
JOIN Check c ON s.check_number = c.check_number
JOIN Store_Product sp ON s.UPC = sp.UPC
JOIN Product p ON sp.id_product = p.id_product         --  НЕПРАВИЛЬНИЙ ЗАПИТ
WHERE p.product_name = 'Назва_товару'
  AND c.print_date BETWEEN '2024-01-01' AND '2024-12-31';

-- Додаткові запити з другого списку:

-- 2. Отримати інформацію про усі товари у магазині, відсортовані за назвою              !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
ORDER BY p.product_name;

-- 3. Отримати інформацію про усіх постійних клієнтів, відсортованих за прізвищем (дублікат запиту 7) !!!   ГОТОВО !!!
SELECT * FROM Customer_Card
ORDER BY cust_surname;

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

-- 12. Отримати інформацію про усі акційні товари (дублікат запиту 15)              !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = TRUE
ORDER BY sp.products_number;

-- 13. Отримати інформацію про усі не акційні товари (дублікат запиту 16)            !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
WHERE sp.promotional_product = FALSE OR sp.promotional_product IS NULL
ORDER BY sp.products_number;

-- 14. За UPC-товару знайти ціну продажу товару, кількість наявних одиниць товару (дублікат запиту 14)   !!!   ГОТОВО !!!
SELECT selling_price, products_number
FROM Store_Product
WHERE UPC = 'UPC_код';


----------------------------------     Кастомні запити     -------------------------------

-- 1. Запит на подвійне заперечення  (застосувати в JdbcCheckDao)           !! WAS TESTED !!
-- Умова: Знайти працівників, які продали хоча б по одному товару з кожної категорії.
-- Запит:

SELECT e.*
FROM Employee e
WHERE NOT EXISTS (
    SELECT *
    FROM Category c
    WHERE NOT EXISTS (
        SELECT *
        FROM Check_table ct
                 INNER JOIN Sale s ON s.check_number = ct.check_number
                 INNER JOIN Store_product sp ON sp.UPC = s.UPC
                 INNER JOIN Product pr ON sp.id_product = pr.id_product
        WHERE ct.id_employee = e.id_employee
          AND pr.category_number = c.category_number
    )
)


-- 2. Запит на групування параметричний (застосувати в JdbcCustomer_cardDao)        !! WAS TESTED !!  !!  WAS CODED  !!
-- Умова: Для обраної карти клієнта, для кожної категорії обрахувати суму знижки, яка була зроблена у всіх чеках.
-- Запит:

SELECT
    c.category_number,
    c.category_name,
    COALESCE(SUM(sa.selling_price / (100 - cc.percent) * cc.percent), 0) AS total_discount
FROM Category c
         LEFT JOIN Product p ON c.category_number = p.category_number
         LEFT JOIN Store_product sp ON p.id_product = sp.id_product
         LEFT JOIN Sale sa ON sp.UPC = sa.UPC
         LEFT JOIN Check_table ct ON sa.check_number = ct.check_number
         LEFT JOIN Customer_Card cc ON ct.card_number = cc.card_number AND cc.card_number = ?
GROUP BY c.category_number, c.category_name
ORDER BY c.category_number;

-- 3.Запит на подвійне заперечення  (застосувати в JdbcCategoryDao)   !! WAS TESTED !!  !!  WAS CODED  !!
-- Умова: Знайти категорії, усі продукти яких є в магазині.
-- Запит:

SELECT c.*
FROM Category c
WHERE NOT EXISTS (
    SELECT *
    FROM Product p
    WHERE p.category_number = c.category_number
      AND NOT EXISTS (
        SELECT *
        FROM Store_product sp
        WHERE sp.id_product = p.id_product
    )
)


-- 4. Запит на групування параметричний (застосувати в JdbcEmployeeDao)           !! WAS TESTED !!   !!  WAS CODED  !!
-- Умова: Для обраного employee для кожної категорії вивести суму податку, який був сплачений в усіх чеках, які сформував цей працівник
-- Запит:
SELECT
    c.category_number,
    c.category_name,
    SUM(s.selling_price) * 0.2 AS total_tax
FROM Employee e
         INNER JOIN Check_table ct ON e.id_employee = ct.id_employee
         INNER JOIN Sale s ON ct.check_number = s.check_number
         INNER JOIN Store_product sp ON s.UPC = sp.UPC
         INNER JOIN Product p ON sp.id_product = p.id_product
         INNER JOIN Category c ON p.category_number = c.category_number
WHERE e.id_employee = ?
GROUP BY c.category_number, c.category_name;

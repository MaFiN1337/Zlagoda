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

-- 21. Визначити загальну кількість одиниць певного товару, проданого за певний період часу              !!!   ГОТОВО !!!
SELECT SUM(s.product_number) as total_quantity
FROM Sale s
JOIN Check_table c ON s.check_number = c.check_number
JOIN Store_Product sp ON s.UPC = sp.UPC
JOIN Product p ON sp.id_product = p.id_product
WHERE p.product_name = 'Назва_товару'
  AND c.print_date BETWEEN '2024-01-01' AND '2024-12-31';

-- Додаткові запити з другого списку:

-- 2. Отримати інформацію про усі товари у магазині, відсортовані за назвою              !!!   ГОТОВО !!!
SELECT sp.*, p.product_name FROM Store_Product sp
JOIN Product p ON sp.id_product = p.id_product
ORDER BY p.product_name;

-- 9. Переглянути список усіх чеків, що створив касир за цей день       !!!   ГОТОВО !!!
SELECT * FROM Check
WHERE id_employee = 'ID_касира'
  AND DATE(print_date) = CURDATE()
ORDER BY print_date;


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

-- 1. Запит на подвійне заперечення  (застосувати в JdbcEmployeeDao)           !! WAS TESTED !!  !!  WAS CODED  !!
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

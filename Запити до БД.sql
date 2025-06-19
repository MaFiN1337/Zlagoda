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

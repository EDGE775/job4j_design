SELECT * FROM product
    WHERE price = (SELECT max(price) FROM product);
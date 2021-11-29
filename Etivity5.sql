CREATE TABLE `pcshop`.`ratings` ( `customer_id` CHAR(10) NOT NULL , `model` CHAR(4) NOT NULL , `rating` INT NOT NULL , `rating_time` TIMESTAMP NOT NULL )
ALTER TABLE `customers` ADD `discount` FLOAT NOT NULL DEFAULT '0' AFTER `email`;
/***/
DELIMITER $$
CREATE FUNCTION GetCurrentRating(cid varchar(10), M varchar(4))
RETURNS INTEGER
DETERMINISTIC
BEGIN
DECLARE DR TIMESTAMP;
DECLARE cRating Int;

SELECT MAX (rating_time)INTO _date FROM ratings WHERE ratings.customer_id = cid AND ratings.model = M
IF DR = null THEN
	set cRating = NULL
ELSE
SELECT ratings INTO cRating FROM ratings WHERE ratings.customer_id = cid AND ratings.model = M AND ratings.rating_time=DR;

END IF;
RETURN(cRating);
END $$

DELIMITER ; 
/***/
DELIMITER $$
CREATE FUNCTION GetNumberOfRatings(cid varchar(10))
RETURNS INTEGER
DETERMINISTIC
BEGIN
DECLARE nNull Integer;

SELECT COUNT(rating) INTO nNull FROM ratings WHERE customer_id - cid and rating !- NULL;
RETURN(nNull);
END $$

DELIMITER;
/***/
DELIMITER $$

CREATE PROCEDURE RateModel(in cid varchar(10) ,in M char(4),in R int(20)) IN
BEGIN

DECLARE old_rating integer;

SELECT rating INTO old_rating FROM ratings WHERE customer_id = cid AND model= m;

IF old_rating = NULL THEN 
	INSERT INTO ratings (customer_id, model, rating, rating_time)
	VALUES(customer_id, model, rating,rating_time);
END IF;

DECLARE NumberOfRatings Int;
DECLARE disc FLOAT;

SELECT discount into disc from customers where customer_id = cid;
SELECT COUNT ratings INTO NumberOfRatings FROM ratings WHERE customer_id = cid AND model = m;

IF NumberOfRatings >= 5 And disc != 10 THEN
	UPDATE customers SET customers.discount = 10.0 Where customer_id = cid;
END IF;

END;

DELIMITER ;





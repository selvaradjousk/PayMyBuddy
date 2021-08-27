insert into 
	transaction (payer_id, beneficiary_id, amount, description, date, commision)
values
	('1', '2', '100', 'description1', '2021-08-27', '0.25'),
	('1', '3', '150', 'description2', '2021-08-27', '0.25'),
	('1', '4', '200', 'description3', '2021-08-27', '0.25'),
	('2', '1', '100', 'description4', '2021-08-27', '0.25'),
	('2', '3', '150', 'description5', '2021-08-27', '0.25'),
	('2', '4', '200', 'description6', '2021-08-27', '0.25'),
	('3', '1', '150', 'description7', '2021-08-27', '0.25'),
	('3', '2', '100', 'description8', '2021-08-27', '0.25'),
	('3', '4', '200', 'description1', '2021-08-27', '0.25'),
	('9', '2', '100', 'description1', '2021-08-27', '0.25');
commit;
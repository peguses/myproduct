insert into products(product_name) values('Penguin-ears');
insert into products(product_name) values('Horseshoe');

insert into cartons(product_id,unit_count ) values((select id from products where product_name='Penguin-ears'), 20);
insert into cartons(product_id,unit_count ) values((select id from products where product_name='Horseshoe'), 5);

insert into product_prices(product_id,price,product_type ) values((select id from products where product_name='Penguin-ears'), 175, 'CARTON');
insert into product_prices(product_id,price,product_type ) values((select id from products where product_name='Horseshoe'), 825, 'CARTON');

insert into discounts(product_id,discount,product_type, discount_type, discount_condition, active) values((select id from products where product_name='Penguin-ears'), 10, 'CARTON', 'CARTON_COUNT', '3', true);
insert into discounts(product_id,discount,product_type, discount_type, discount_condition, active) values((select id from products where product_name='Horseshoe'), 10, 'CARTON', 'CARTON_COUNT', '3', true);

insert into costs(product_id,product_type, cost_type, cost_calculate_factor) values((select id from products where product_name='Horseshoe'),'UNIT', 'UNIT_PREP_LABOUR_COST', '1.3');
insert into costs(product_id,product_type, cost_type, cost_calculate_factor) values((select id from products where product_name='Penguin-ears'), 'UNIT', 'UNIT_PREP_LABOUR_COST', '1.3');
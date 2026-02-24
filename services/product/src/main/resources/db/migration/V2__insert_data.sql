insert into category (id, name, description) values 
(1, 'Electronics', 'Electronic gadgets and devices'),
(2, 'Books', 'Educational and non-fiction books'),
(3, 'Clothing', 'Men and Women apparel'),
(4, 'Home Appliances', 'Appliances for home use');

insert into product 
(id, name, description, available_quantity, price, category_id) 
values
(101, 'iPhone 15', 'Latest Apple smartphone', 10, 79999.00, 1),

(102, 'Dell XPS 13', 'Lightweight performance laptop', 5, 125000.00, 1),

(103, 'Clean Code', 'Book by Robert C. Martin', 20, 450.00, 2),

(104, 'Discrete Mathematics', 'Engineering mathematics textbook', 15, 699.00, 2),

(105, 'Men T-Shirt', 'Cotton casual wear', 50, 499.00, 3),

(106, 'Microwave Oven', '800W convection microwave', 7, 8999.00, 4);
SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('トップス', 'トップスのカテゴリーです'),
('ボトムス', 'ボトムスのカテゴリーです'),
('シューズ', 'シューズのカテゴリーです');
;

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company) VALUES 
('Tシャツ','てぃーしゃつ','汗を吸収し、快適に過ごせます。',1,1000,'/img/summer T-shi.jpg','2024/08/05','シロクロ'),
('ブラウス','ぶらうす','どんな服にも合わせやすいです。',1,1300,'/img/blouse.jpg','2024/07/25','服の赤山'),
('スカート','すかーと','流行りのデザインのスカートです。',2,1200,'/img/skirt.jpg','2024/01/05','ファッションセンター山村'),
('デニム','でにむ','３Lまで揃っています。',2,13350,'/img/jeans.jpg','2023/11/05','ビッグサイズスリーエル'),
('スニーカー','すにーかー','歩いても疲れにくいです。',3,5900,'/img/sneakers.jpg','2024/02/14','アルキ'),
('サンダル','さんだる','色が映えるサンダルです。',3,1750,'/img/sandal.jpg','2024/06/18','海専門店');
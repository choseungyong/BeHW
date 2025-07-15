INSERT INTO category (name, color, image_url, description) VALUES
  ('교환권',    '#6c95d1', 'https://gift-s.kakaocdn.net/dn/gift/images/m640/dimm_theme.png', ''),
  ('상품권',    '#f2c94c', 'https://example.com/images/giftcard.png', ''),
  ('뷰티',      '#e68fac', 'https://example.com/images/beauty.png', ''),
  ('패션',      '#5cb85c', 'https://example.com/images/fashion.png', ''),
  ('식품',      '#f0ad4e', 'https://example.com/images/food.png', ''),
  ('리빙/도서', '#0275d8', 'https://example.com/images/living.png', '');

INSERT INTO products (name, price, image_url, category_id) VALUES
  ('아이스 아메리카노', 4500, 'https://cdn-icons-png.flaticon.com/512/924/924514.png',
      (SELECT id FROM category WHERE name = '교환권')),
  ('초코 케이크',       5500, 'https://cdn-icons-png.flaticon.com/512/685/685352.png',
      (SELECT id FROM category WHERE name = '식품')),
  ('오렌지 주스',       4000, 'https://cdn-icons-png.flaticon.com/512/135/135620.png',
      (SELECT id FROM category WHERE name = '식품')),
  ('핫도그',             5200, 'https://cdn-icons-png.flaticon.com/512/1046/1046784.png',
      (SELECT id FROM category WHERE name = '식품'));

INSERT INTO options (name, quantity, product_id) VALUES
  ('기본 옵션',        10, 1),
  ('추가 토핑 옵션',   5,  1);

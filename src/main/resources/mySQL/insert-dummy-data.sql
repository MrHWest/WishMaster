-- Insert a row into the wishlist table with a password
INSERT INTO wishmaster.wishlist (password) VALUES ('password123');

-- Get the ID of the wishlist row that was just inserted
SET @wishlistID = LAST_INSERT_ID();

-- Insert rows into the Items table with the wishlistID
INSERT INTO wishmaster.item (Title, link, wishlist_id) VALUES ('APPLE AIRPODS 2ND GEN 2019', 'https://www.power.dk/tv-og-lyd/hovedtelefoner/true-wireless-hovedtelefoner/apple-airpods-2nd-gen-2019/p-1513023/', @wishlistID);
INSERT INTO wishmaster.item (Title, link, wishlist_id) VALUES ('Samsung TV', 'https://www.power.dk/tv-og-lyd/tv/samsung-60-4k-led-tv-ue60bu8075uxxc/p-1327555/', @wishlistID);

-- Insert a row into the wishlist table with a password
INSERT INTO wishmaster.wishlist (password) VALUES ('password456');

-- Get the ID of the wishlist row that was just inserted
SET @wishlistID = LAST_INSERT_ID();

-- Insert rows into the Items table with the wishlistID
INSERT INTO wishmaster.item (Title, link, wishlist_id) VALUES ('Effaclar DUO+ ansigtscreme 40 ml', 'https://www.matas.dk/la-roche-posay-effaclar-duo-ansigtscreme-40-ml', @wishlistID);
INSERT INTO wishmaster.item (Title, link, wishlist_id) VALUES ('Pure Glow Purifying Day Cream 50 ml', 'https://www.matas.dk/aco-purifying-day-cream-50-ml', @wishlistID);

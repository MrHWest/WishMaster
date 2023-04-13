SELECT wishlist.id, item.title, item.link
FROM wishlist
INNER JOIN item ON wishlist.id = item.wishlistID;
USE `cdas`;
START TRANSACTION;
INSERT INTO `cdas`.`role_has_permission`(role_id, permission_id)
VALUES
-- OWNER
(1, 1),  -- Read All
(1, 2),  -- Write All
(1, 3),  -- Update All
(1, 4),  -- Delete All
-- SUPER_ADMIN
(2, 5),  -- Read Permissions
(2, 6),  -- Write Permissions
(2, 7),  -- Update Permissions
(2, 8),  -- Delete Permissions
(2, 17), -- Read Super Admins
(2, 13), -- Read Admins
(2, 14), -- Write Admins
(2, 15), -- Update Admins
(2, 16), -- Delete Admins
(2, 21), -- Read Self
(2, 22), -- Write Self
(2, 23), -- Update Self
(2, 24), -- Delete Self
(2, 37), -- Read Device Metadata
(2, 38), -- Write Device Metadata
(2, 39), -- Update Device Metadata
(2, 40), -- Delete Device Metadata
-- ADMIN
(3, 21), -- Read Self
(3, 22), -- Write Self
(3, 23), -- Update Self
(3, 24), -- Delete Self
-- MANAGER
(4, 21), -- Read Self
(4, 22), -- Write Self
(4, 23), -- Update Self
(4, 24), -- Delete Self
-- USER
(5, 21), -- Read Self
(5, 22), -- Write Self
(5, 23), -- Update Self
(5, 24); -- Delete Self
COMMIT;

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
-- permissions
(2, 5),  -- Read All Permissions
(2, 6),  -- Read Permission
(2, 7),  -- Write Permission
-- roles
(2, 11), -- Read All Roles
(2, 12), -- Read Role
(2, 13), -- Write Role
-- accounts
(2, 17), -- Read All Accounts
(2, 18), -- Read Account
(2, 19), -- Write Account
(2, 20), -- Update Account Full Name
(2, 21), -- Update Account User Image
(2, 24), -- Delete Account
-- device metadata
(2, 30), -- Read Device Metadata
(2, 31), -- Read All User Device Metadata
(2, 32), -- Read All Device Metadata
(2, 33), -- Read Device Types
(2, 34), -- Write Device Metadata
(2, 35), -- Update Device Metadata
(2, 36), -- Update Device Metadata Unlink From User
(2, 37), -- Update Device Metadata Link To User
(2, 38), -- Delete Device Metadata
(2, 39), -- Delete All Device Metadata
-- device regular schedule
(2, 40), -- Read All Device Regular Schedules
(2, 41), -- Write Device Regular Schedule
(2, 42), -- Update Device Regular Schedule
(2, 43), -- Delete Device Regular Schedule
-- hydroponic setting template
(2, 44), -- Read All Hydroponic Setting Templates
(2, 45), -- Write Hydroponic Setting Template
(2, 46), -- Update Hydroponic Setting Template
(2, 47), -- Delete Hydroponic Setting Template
-- hydroponic operation
(2, 51), -- Update Hydroponic Time
(2, 52), -- Update Hydroponic Time Zone
(2, 53), -- Update Hydroponic Autotime Mode
(2, 54), -- Update Hydroponic Ph Up Action
(2, 55), -- Update Hydroponic Ph Down Action
(2, 56), -- Update Hydroponic Tds Action
(2, 57), -- Update Hydroponic Restart
(2, 58), -- Update Hydroponic Save Settings
(2, 59), -- Update Hydroponic Read Settings
(2, 60), -- Update Hydroponic Calibrate Tds
(2, 61), -- Update Hydroponic Clear Tds Calibration
(2, 62), -- Update Hydroponic Calibrate Ph Low
(2, 63), -- Update Hydroponic Calibrate Ph High
(2, 64), -- Update Hydroponic Clear Ph Calibration
(2, 65), -- Update Hydroponic Setup Ph
(2, 66), -- Update Hydroponic Setup Tds
(2, 67), -- Update Hydroponic Dispensers Recheck Time
(2, 68), -- Update Hydroponic Ph Up Dose
(2, 69), -- Update Hydroponic Ph Down Dose
(2, 70), -- Update Hydroponic Tds Dose
(2, 71), -- Update Hydroponic Regulator Error Ph
(2, 72), -- Update Hydroponic Regulator Error Tds
(2, 73), -- Update Hydroponic Pump Speed
(2, 74), -- Update Hydroponic Wifi Ssid
(2, 75), -- Update Hydroponic Wifi Password
(2, 76), -- Update Hydroponic Enable Sensors
(2, 77), -- Update Hydroponic Enable Dispensers
-- hydroponic calibration data
(2, 78), -- Read All Hydroponic Calibration Data
(2, 79), -- Delete Hydroponic Calibration Data


-- ADMIN
-- permissions
(3, 6),  -- Read Permission
(3, 7),  -- Write Permission
-- roles
(3, 12), -- Read Role
(3, 13), -- Write Role
-- accounts
(3, 17), -- Read All Accounts
(3, 18), -- Read Account
(3, 19), -- Write Account
(3, 20), -- Update Account Full Name
(3, 21), -- Update Account User Image
(3, 24), -- Delete Account
-- device metadata
(3, 30), -- Read Device Metadata
(3, 31), -- Read All User Device Metadata
(3, 32), -- Read All Device Metadata
(3, 33), -- Read Device Types
(3, 35), -- Update Device Metadata
(3, 36), -- Update Device Metadata Unlink From User
(3, 37), -- Update Device Metadata Link To User
-- device regular schedule
(3, 40), -- Read All Device Regular Schedules
(3, 41), -- Write Device Regular Schedule
(3, 42), -- Update Device Regular Schedule
(3, 43), -- Delete Device Regular Schedule
-- hydroponic setting template
(3, 44), -- Read All Hydroponic Setting Templates
(3, 45), -- Write Hydroponic Setting Template
(3, 46), -- Update Hydroponic Setting Template
(3, 47), -- Delete Hydroponic Setting Template


-- MANAGER
-- accounts
(4, 17), -- Read All Accounts
(4, 18), -- Read Account
(4, 20), -- Update Account Full Name
(4, 21), -- Update Account User Image
-- device metadata
(4, 30), -- Read Device Metadata
(4, 31), -- Read All User Device Metadata
(4, 32), -- Read All Device Metadata
(4, 33), -- Read Device Types
(4, 34), -- Write Device Metadata
(4, 35), -- Update Device Metadata
(4, 36), -- Update Device Metadata Unlink From User
(4, 37), -- Update Device Metadata Link To User
(4, 38), -- Delete Device Metadata
(4, 39), -- Delete All Device Metadata
-- hydroponic setting template
(4, 44), -- Read All Hydroponic Setting Templates
(4, 45), -- Write Hydroponic Setting Template
(4, 46), -- Update Hydroponic Setting Template
(4, 47), -- Delete Hydroponic Setting Template


-- USER
-- general
(5, 26), -- Read Self
(5, 27), -- Write Self
(5, 28), -- Update Self
(5, 29), -- Delete Self
-- device metadata
(5, 30), -- Read Device Metadata
(5, 31), -- Read All User Device Metadata
(5, 32), -- Read All Device Metadata
(5, 33), -- Read Device Types
(5, 35), -- Update Device Metadata
(5, 36), -- Update Device Metadata Unlink From User
(5, 37), -- Update Device Metadata Link To User
-- device regular schedule
(5, 40), -- Read All Device Regular Schedules
(5, 41), -- Write Device Regular Schedule
(5, 42), -- Update Device Regular Schedule
(5, 43), -- Delete Device Regular Schedule
-- hydroponic setting template
(5, 44), -- Read All Hydroponic Setting Templates
(5, 45), -- Write Hydroponic Setting Template
(5, 46), -- Update Hydroponic Setting Template
(5, 47), -- Delete Hydroponic Setting Template
-- hydroponic setting
(5, 48), -- Read All Hydroponic Setting
-- hydroponic data
(5, 49), -- Read All Hydroponic Data
(5, 50), -- Delete All Hydroponic Data
-- hydroponic operation
(5, 51), -- Update Hydroponic Time
(5, 52), -- Update Hydroponic Time Zone
(5, 53), -- Update Hydroponic Autotime Mode
(5, 54), -- Update Hydroponic Ph Up Action
(5, 55), -- Update Hydroponic Ph Down Action
(5, 56), -- Update Hydroponic Tds Action
(5, 57), -- Update Hydroponic Restart
(5, 58), -- Update Hydroponic Save Settings
(5, 59), -- Update Hydroponic Read Settings
(5, 60), -- Update Hydroponic Calibrate Tds
(5, 61), -- Update Hydroponic Clear Tds Calibration
(5, 62), -- Update Hydroponic Calibrate Ph Low
(5, 63), -- Update Hydroponic Calibrate Ph High
(5, 64), -- Update Hydroponic Clear Ph Calibration
(5, 65), -- Update Hydroponic Setup Ph
(5, 66), -- Update Hydroponic Setup Tds
(5, 67), -- Update Hydroponic Dispensers Recheck Time
(5, 68), -- Update Hydroponic Ph Up Dose
(5, 69), -- Update Hydroponic Ph Down Dose
(5, 70), -- Update Hydroponic Tds Dose
(5, 71), -- Update Hydroponic Regulator Error Ph
(5, 72), -- Update Hydroponic Regulator Error Tds
(5, 73), -- Update Hydroponic Pump Speed
(5, 74), -- Update Hydroponic Wifi Ssid
(5, 75), -- Update Hydroponic Wifi Password
(5, 76), -- Update Hydroponic Enable Sensors
(5, 77), -- Update Hydroponic Enable Dispensers
-- hydroponic calibration data
(5, 78), -- Read All Hydroponic Calibration Data
(5, 79), -- Delete Hydroponic Calibration Data

-- SECURITY_DIRECTOR
-- permissions
(6, 5),  -- Read All Permissions
(6, 6),  -- Read Permission
(6, 7),  -- Write Permission
(6, 8),  -- Update Permission
(6, 9),  -- Delete Permission
(6, 10), -- Delete All Permissions
-- roles
(6, 11), -- Read All Roles
(6, 12), -- Read Role
(6, 13), -- Write Role
(6, 14), -- Update Role
(6, 15), -- Delete Role
(6, 16), -- Delete All Roles
-- accounts
(6, 17), -- Read All Accounts
(6, 18), -- Read Account
(6, 20), -- Update Account Full Name
(6, 21), -- Update Account User Image
(6, 22), -- Update Account
(6, 24), -- Delete Account
(6, 23), -- Delete All Accounts
(6, 25); -- Delete Account Permanent

COMMIT;

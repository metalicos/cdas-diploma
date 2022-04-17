USE `cdas`;
START TRANSACTION;
INSERT INTO `cdas`.`permission` (id, name, value)
VALUES (1, 'Read All', 'r_all'),
       (2, 'Write All', 'w_all'),
       (3, 'Update All', 'u_all'),
       (4, 'Delete All', 'd_all'),

       (5, 'Read All Permissions', 'r_all_permissions'),
       (6, 'Read Permission', 'r_permission'),
       (7, 'Write Permission', 'w_permission'),
       (8, 'Update Permission', 'u_permission'),
       (9, 'Delete Permission', 'd_permission'),
       (10, 'Delete All Permissions', 'd_all_permissions'),

       (11, 'Read All Roles', 'r_all_roles'),
       (12, 'Read Role', 'r_role'),
       (13, 'Write Role', 'w_role'),
       (14, 'Update Role', 'u_role'),
       (15, 'Delete Role', 'd_role'),
       (16, 'Delete All Roles', 'd_all_roles'),

       (17, 'Read All Accounts', 'r_all_accounts'),
       (18, 'Read Account', 'r_account'),
       (19, 'Write Account', 'w_account'),
       (20, 'Update Account Full Name', 'u_account_full_name'),
       (21, 'Update Account User Image', 'u_user_account_image'),
       (22, 'Update Account', 'u_account'),
       (23, 'Delete All Accounts', 'd_all_accounts'),
       (24, 'Delete Account', 'd_account'),
       (25, 'Delete Account Permanent', 'd_account_permanent'),

       (26, 'Read Self', 'r_self'),
       (27, 'Write Self', 'w_self'),
       (28, 'Update Self', 'u_self'),
       (29, 'Delete Self', 'd_self'),

       (30, 'Read Device Metadata', 'r_device_metadata'),
       (31, 'Read All User Device Metadata', 'r_all_user_device_metadata'),
       (32, 'Read All Device Metadata', 'r_all_device_metadata'),
       (33, 'Read Device Types', 'r_device_types'),
       (34, 'Write Device Metadata', 'w_device_metadata'),
       (35, 'Update Device Metadata', 'u_device_metadata'),
       (36, 'Update Device Metadata Unlink From User', 'u_device_metadata_unlink'),
       (37, 'Update Device Metadata Link To User', 'u_device_metadata_link'),
       (38, 'Delete Device Metadata', 'd_device_metadata'),
       (39, 'Delete All Device Metadata', 'd_all_device_metadata'),

       (40, 'Read All Device Regular Schedules', 'r_all_device_regular_schedules'),
       (41, 'Write Device Regular Schedule', 'w_device_regular_schedule'),
       (42, 'Update Device Regular Schedule', 'u_device_regular_schedule'),
       (43, 'Delete Device Regular Schedule', 'd_device_regular_schedule'),

       (44, 'Read All Hydroponic Setting Templates', 'r_all_hydroponic_setting_templates'),
       (45, 'Write Hydroponic Setting Template', 'w_hydroponic_setting_template'),
       (46, 'Update Hydroponic Setting Template', 'u_hydroponic_setting_template'),
       (47, 'Delete Hydroponic Setting Template', 'd_hydroponic_setting_template'),

       (48, 'Read All Hydroponic Setting', 'r_all_hydroponic_setting'),

       (49, 'Read All Hydroponic Data', 'r_all_hydroponic_data'),
       (50, 'Delete All Hydroponic Data', 'd_all_hydroponic_data'),

       (51, 'Update Hydroponic Time', 'u_hydroponic_time'),
       (52, 'Update Hydroponic Time Zone', 'u_hydroponic_time_zone'),
       (53, 'Update Hydroponic Autotime Mode', 'u_hydroponic_autotime'),
       (54, 'Update Hydroponic Ph Up Action', 'u_hydroponic_ph_up'),
       (55, 'Update Hydroponic Ph Down Action', 'u_hydroponic_ph_down'),
       (56, 'Update Hydroponic Tds Action', 'u_hydroponic_tds'),
       (57, 'Update Hydroponic Restart', 'u_hydroponic_restart'),
       (58, 'Update Hydroponic Save Settings', 'u_hydroponic_save_settings'),
       (59, 'Update Hydroponic Read Settings', 'u_hydroponic_read_settings'),
       (60, 'Update Hydroponic Calibrate Tds', 'u_hydroponic_calibrate_tds'),
       (61, 'Update Hydroponic Clear Tds Calibration', 'u_hydroponic_calibrate_tds_clear'),
       (62, 'Update Hydroponic Calibrate Ph Low', 'u_hydroponic_calibrate_ph_low'),
       (63, 'Update Hydroponic Calibrate Ph High', 'u_hydroponic_calibrate_ph_high'),
       (64, 'Update Hydroponic Clear Ph Calibration', 'u_hydroponic_calibrate_ph_clear'),
       (65, 'Update Hydroponic Setup Ph', 'u_hydroponic_setup_ph'),
       (66, 'Update Hydroponic Setup Tds', 'u_hydroponic_setup_tds'),
       (67, 'Update Hydroponic Dispensers Recheck Time', 'u_hydroponic_dispensers_recheck_time'),
       (68, 'Update Hydroponic Ph Up Dose', 'u_hydroponic_dose_ph_up'),
       (69, 'Update Hydroponic Ph Down Dose', 'u_hydroponic_dose_ph_down'),
       (70, 'Update Hydroponic Tds Dose', 'u_hydroponic_dose_tds'),
       (71, 'Update Hydroponic Regulator Error Ph', 'u_hydroponic_regulator_error_ph'),
       (72, 'Update Hydroponic Regulator Error Tds', 'u_hydroponic_regulator_error_tds'),
       (73, 'Update Hydroponic Pump Speed', 'u_hydroponic_pump_speed'),
       (74, 'Update Hydroponic Wifi Ssid', 'u_hydroponic_wifi_ssid'),
       (75, 'Update Hydroponic Wifi Password', 'u_hydroponic_wifi_pass'),
       (76, 'Update Hydroponic Enable Sensors', 'u_hydroponic_enable_sensors'),
       (77, 'Update Hydroponic Enable Dispensers', 'u_hydroponic_enable_dispensers'),

       (78, 'Read All Hydroponic Calibration Data', 'r_all_hydroponic_calibration_data'),
       (79, 'Delete Hydroponic Calibration Data', 'd_hydroponic_calibration_data'),
       (80, 'Update Hydroponic PH Sensor Calibration From Database Data', 'u_hydroponic_ph_calibration_from_database'),
       (81, 'Update Hydroponic TDS Sensor Calibration From Database Data', 'u_hydroponic_tds_calibration_from_database'),

       (82, 'Read Delegated Device Control Using Self Token', 'r_self_delegated_device_control'),
       (83, 'Read Delegated Device Control', 'r_delegated_device_control'),
       (84, 'Update Delegated Device Control', 'u_delegated_device_control'),
       (85, 'Create Delegated Device Control', 'w_delegated_device_control_request');

COMMIT;

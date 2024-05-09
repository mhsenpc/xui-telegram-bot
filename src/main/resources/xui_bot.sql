-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: May 09, 2024 at 07:24 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `xui_bot`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `client_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `url` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int DEFAULT NULL,
  `uuid` varchar(50) NOT NULL,
  `order_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `valid_until` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`client_id`, `name`, `url`, `user_id`, `uuid`, `order_id`, `created_at`, `valid_until`) VALUES
(1, 'user_DmIO0', 'todo:// implement this method', 12, '13a76b9b-85d3-4b0a-a50d-3f91977db77b', 1, '2024-05-09 21:23:20', '2024-06-09 21:23:20');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` int NOT NULL,
  `plan_id` int DEFAULT NULL,
  `payment_method` int NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `status`, `plan_id`, `payment_method`, `created_at`) VALUES
(1, 12, 2, 15, 0, '2024-05-01 19:48:18'),
(2, 12, 2, 16, 0, '2024-05-01 20:52:40'),
(3, 12, 2, 16, 0, '2024-05-09 21:16:48');

-- --------------------------------------------------------

--
-- Table structure for table `photos`
--

CREATE TABLE `photos` (
  `photo_id` int NOT NULL,
  `telegram_file_id` varchar(200) NOT NULL,
  `width` int NOT NULL,
  `height` int NOT NULL,
  `file_size` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `photos`
--

INSERT INTO `photos` (`photo_id`, `telegram_file_id`, `width`, `height`, `file_size`, `order_id`, `created_at`) VALUES
(1, 'AgACAgQAAxkBAAIKdGYaXT9iki45yNULf3tVVuO59QTbAAJmwDEbv53QUNFTr5sgnmAFAQADAgADcwADNAQ', 90, 90, 1840, 7, '2024-04-13 12:23:59'),
(2, 'AgACAgQAAxkBAAIKdGYaXT9iki45yNULf3tVVuO59QTbAAJmwDEbv53QUNFTr5sgnmAFAQADAgADbQADNAQ', 320, 320, 16979, 7, '2024-04-13 12:23:59'),
(3, 'AgACAgQAAxkBAAIKdGYaXT9iki45yNULf3tVVuO59QTbAAJmwDEbv53QUNFTr5sgnmAFAQADAgADeAADNAQ', 800, 800, 48362, 7, '2024-04-13 12:23:59'),
(4, 'AgACAgQAAxkBAAIKdGYaXT9iki45yNULf3tVVuO59QTbAAJmwDEbv53QUNFTr5sgnmAFAQADAgADeQADNAQ', 1080, 1080, 67251, 7, '2024-04-13 12:23:59'),
(5, 'AgACAgQAAxkBAAIKfmYaXffMrYq43mBB5T42dS6U6mpgAAJmwDEbv53QUNFTr5sgnmAFAQADAgADcwADNAQ', 90, 90, 1840, 10, '2024-04-13 12:27:03'),
(6, 'AgACAgQAAxkBAAIKfmYaXffMrYq43mBB5T42dS6U6mpgAAJmwDEbv53QUNFTr5sgnmAFAQADAgADbQADNAQ', 320, 320, 16979, 10, '2024-04-13 12:27:03'),
(7, 'AgACAgQAAxkBAAIKfmYaXffMrYq43mBB5T42dS6U6mpgAAJmwDEbv53QUNFTr5sgnmAFAQADAgADeAADNAQ', 800, 800, 48362, 10, '2024-04-13 12:27:03'),
(8, 'AgACAgQAAxkBAAIKfmYaXffMrYq43mBB5T42dS6U6mpgAAJmwDEbv53QUNFTr5sgnmAFAQADAgADeQADNAQ', 1080, 1080, 67251, 10, '2024-04-13 12:27:03'),
(9, 'AgACAgQAAxkBAAIKhGYaXgw8QskxRpnmpS8bEHKrBX0kAAKHwDEbv53QUOxwfjhcIH7HAQADAgADcwADNAQ', 90, 51, 1396, 11, '2024-04-13 12:27:26'),
(10, 'AgACAgQAAxkBAAIKhGYaXgw8QskxRpnmpS8bEHKrBX0kAAKHwDEbv53QUOxwfjhcIH7HAQADAgADbQADNAQ', 320, 180, 19754, 11, '2024-04-13 12:27:26'),
(11, 'AgACAgQAAxkBAAIKhGYaXgw8QskxRpnmpS8bEHKrBX0kAAKHwDEbv53QUOxwfjhcIH7HAQADAgADeAADNAQ', 800, 450, 98314, 11, '2024-04-13 12:27:26'),
(12, 'AgACAgQAAxkBAAIKhGYaXgw8QskxRpnmpS8bEHKrBX0kAAKHwDEbv53QUOxwfjhcIH7HAQADAgADeQADNAQ', 1280, 720, 220821, 11, '2024-04-13 12:27:26'),
(13, 'AgACAgQAAxkBAAIKhGYaXgw8QskxRpnmpS8bEHKrBX0kAAKHwDEbv53QUOxwfjhcIH7HAQADAgADdwADNAQ', 1600, 900, 245346, 11, '2024-04-13 12:27:26'),
(14, 'AgACAgQAAxkBAAIKoGYa2VoWHAGK1orIyCEdg6G0BvAZAAJowjEbv53YUGB9WYa1ZOkWAQADAgADcwADNAQ', 90, 90, 1840, 12, '2024-04-13 21:13:31'),
(15, 'AgACAgQAAxkBAAIKoGYa2VoWHAGK1orIyCEdg6G0BvAZAAJowjEbv53YUGB9WYa1ZOkWAQADAgADbQADNAQ', 320, 320, 16979, 12, '2024-04-13 21:13:31'),
(16, 'AgACAgQAAxkBAAIKoGYa2VoWHAGK1orIyCEdg6G0BvAZAAJowjEbv53YUGB9WYa1ZOkWAQADAgADeAADNAQ', 800, 800, 48362, 12, '2024-04-13 21:13:31'),
(17, 'AgACAgQAAxkBAAIKoGYa2VoWHAGK1orIyCEdg6G0BvAZAAJowjEbv53YUGB9WYa1ZOkWAQADAgADeQADNAQ', 1080, 1080, 67251, 12, '2024-04-13 21:13:31'),
(18, 'AgACAgQAAxkBAAIK2WYo10r3DuB5mkaNGnrYIausnLj5AALMxDEb3QtJUbRdC5tqG23qAQADAgADcwADNAQ', 90, 90, 1840, 13, '2024-04-24 11:56:26'),
(19, 'AgACAgQAAxkBAAIK2WYo10r3DuB5mkaNGnrYIausnLj5AALMxDEb3QtJUbRdC5tqG23qAQADAgADbQADNAQ', 320, 320, 16979, 13, '2024-04-24 11:56:26'),
(20, 'AgACAgQAAxkBAAIK2WYo10r3DuB5mkaNGnrYIausnLj5AALMxDEb3QtJUbRdC5tqG23qAQADAgADeAADNAQ', 800, 800, 48362, 13, '2024-04-24 11:56:26'),
(21, 'AgACAgQAAxkBAAIK2WYo10r3DuB5mkaNGnrYIausnLj5AALMxDEb3QtJUbRdC5tqG23qAQADAgADeQADNAQ', 1080, 1080, 67251, 13, '2024-04-24 11:56:26'),
(22, 'AgACAgQAAxkBAAIK6WYo2jvwIGNvG1e1oI1JuAasJHw9AALMxDEb3QtJUbRdC5tqG23qAQADAgADcwADNAQ', 90, 90, 1840, 14, '2024-04-24 12:09:00'),
(23, 'AgACAgQAAxkBAAIK6WYo2jvwIGNvG1e1oI1JuAasJHw9AALMxDEb3QtJUbRdC5tqG23qAQADAgADbQADNAQ', 320, 320, 16979, 14, '2024-04-24 12:09:00'),
(24, 'AgACAgQAAxkBAAIK6WYo2jvwIGNvG1e1oI1JuAasJHw9AALMxDEb3QtJUbRdC5tqG23qAQADAgADeAADNAQ', 800, 800, 48362, 14, '2024-04-24 12:09:00'),
(25, 'AgACAgQAAxkBAAIK6WYo2jvwIGNvG1e1oI1JuAasJHw9AALMxDEb3QtJUbRdC5tqG23qAQADAgADeQADNAQ', 1080, 1080, 67251, 14, '2024-04-24 12:09:00'),
(26, 'AgACAgQAAxkBAAILHGYujm8qK3awfu-HVJyHvo3jXiaZAAJJwDEbRuZ4Uf93NMzieMxAAQADAgADcwADNAQ', 90, 47, 1053, 15, '2024-04-28 19:59:12'),
(27, 'AgACAgQAAxkBAAILHGYujm8qK3awfu-HVJyHvo3jXiaZAAJJwDEbRuZ4Uf93NMzieMxAAQADAgADbQADNAQ', 320, 168, 14047, 15, '2024-04-28 19:59:12'),
(28, 'AgACAgQAAxkBAAILHGYujm8qK3awfu-HVJyHvo3jXiaZAAJJwDEbRuZ4Uf93NMzieMxAAQADAgADeAADNAQ', 377, 198, 17019, 15, '2024-04-28 19:59:12'),
(29, 'AgACAgQAAxkBAAILiWYyfVfJZM7XkQKAYuNyW0Zu9JhQAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADcwADNAQ', 90, 72, 933, 22, '2024-05-01 19:35:20'),
(30, 'AgACAgQAAxkBAAILiWYyfVfJZM7XkQKAYuNyW0Zu9JhQAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADbQADNAQ', 320, 257, 13444, 22, '2024-05-01 19:35:20'),
(31, 'AgACAgQAAxkBAAILiWYyfVfJZM7XkQKAYuNyW0Zu9JhQAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeAADNAQ', 800, 642, 49989, 22, '2024-05-01 19:35:20'),
(32, 'AgACAgQAAxkBAAILiWYyfVfJZM7XkQKAYuNyW0Zu9JhQAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeQADNAQ', 986, 791, 61999, 22, '2024-05-01 19:35:20'),
(33, 'AgACAgQAAxkBAAILrGYygGLl_VSDPhZo2yLL31W606LmAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADcwADNAQ', 90, 72, 933, 1, '2024-05-01 19:48:18'),
(34, 'AgACAgQAAxkBAAILrGYygGLl_VSDPhZo2yLL31W606LmAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADbQADNAQ', 320, 257, 13444, 1, '2024-05-01 19:48:18'),
(35, 'AgACAgQAAxkBAAILrGYygGLl_VSDPhZo2yLL31W606LmAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeAADNAQ', 800, 642, 49989, 1, '2024-05-01 19:48:18'),
(36, 'AgACAgQAAxkBAAILrGYygGLl_VSDPhZo2yLL31W606LmAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeQADNAQ', 986, 791, 61999, 1, '2024-05-01 19:48:18'),
(37, 'AgACAgQAAxkBAAIL1WYyj3dgas2KJa64q5lmUbmZ6vFdAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADcwADNAQ', 90, 72, 933, 2, '2024-05-01 20:52:40'),
(38, 'AgACAgQAAxkBAAIL1WYyj3dgas2KJa64q5lmUbmZ6vFdAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADbQADNAQ', 320, 257, 13444, 2, '2024-05-01 20:52:40'),
(39, 'AgACAgQAAxkBAAIL1WYyj3dgas2KJa64q5lmUbmZ6vFdAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeAADNAQ', 800, 642, 49989, 2, '2024-05-01 20:52:40'),
(40, 'AgACAgQAAxkBAAIL1WYyj3dgas2KJa64q5lmUbmZ6vFdAAJBwzEb_kORUT5nz_Sf7pjQAQADAgADeQADNAQ', 986, 791, 61999, 2, '2024-05-01 20:52:40'),
(41, 'AgACAgQAAxkBAAIMFGY9IR-lzq1L75TBLZwOQl6APt0IAAJCwTEbLyPxUSdljFsWwREXAQADAgADcwADNQQ', 90, 64, 826, 3, '2024-05-09 21:16:48'),
(42, 'AgACAgQAAxkBAAIMFGY9IR-lzq1L75TBLZwOQl6APt0IAAJCwTEbLyPxUSdljFsWwREXAQADAgADbQADNQQ', 320, 228, 11693, 3, '2024-05-09 21:16:48'),
(43, 'AgACAgQAAxkBAAIMFGY9IR-lzq1L75TBLZwOQl6APt0IAAJCwTEbLyPxUSdljFsWwREXAQADAgADeAADNQQ', 800, 571, 44276, 3, '2024-05-09 21:16:48'),
(44, 'AgACAgQAAxkBAAIMFGY9IR-lzq1L75TBLZwOQl6APt0IAAJCwTEbLyPxUSdljFsWwREXAQADAgADeQADNQQ', 1095, 782, 62922, 3, '2024-05-09 21:16:48');

-- --------------------------------------------------------

--
-- Table structure for table `plans`
--

CREATE TABLE `plans` (
  `plan_id` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `price` float NOT NULL,
  `months` int NOT NULL,
  `traffic_limit` int NOT NULL,
  `connection_limit` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `plans`
--

INSERT INTO `plans` (`plan_id`, `title`, `price`, `months`, `traffic_limit`, `connection_limit`) VALUES
(15, 'Product Operations Representative', 50, 1, 20, 0),
(16, 'Future Functionality Planner', 100, 2, 40, 0);

-- --------------------------------------------------------

--
-- Table structure for table `test_configs`
--

CREATE TABLE `test_configs` (
  `test_config_id` int NOT NULL,
  `url` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `amount` float NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `user_id`, `amount`, `description`, `order_id`, `created_at`) VALUES
(1, 12, 100, NULL, 2, '2024-05-04 15:46:20'),
(2, 12, 100, NULL, 3, '2024-05-09 21:17:15'),
(3, 12, 50, NULL, 1, '2024-05-09 21:23:20');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `first_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chat_id` varchar(50) NOT NULL,
  `credit` float NOT NULL,
  `status` int NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `username`, `chat_id`, `credit`, `status`, `created_at`) VALUES
(12, 'Mohsen', NULL, 'mohsen8482', '115139439', 0, 1, '2024-05-01 19:46:52');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_role_id` int NOT NULL,
  `role` tinyint NOT NULL,
  `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `role`, `user_id`) VALUES
(4, 1, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`client_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indexes for table `photos`
--
ALTER TABLE `photos`
  ADD PRIMARY KEY (`photo_id`);

--
-- Indexes for table `plans`
--
ALTER TABLE `plans`
  ADD PRIMARY KEY (`plan_id`);

--
-- Indexes for table `test_configs`
--
ALTER TABLE `test_configs`
  ADD PRIMARY KEY (`test_config_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `chat_id` (`chat_id`),
  ADD KEY `user_id` (`user_id`) USING BTREE;

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_role_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `photos`
--
ALTER TABLE `photos`
  MODIFY `photo_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `plans`
--
ALTER TABLE `plans`
  MODIFY `plan_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `test_configs`
--
ALTER TABLE `test_configs`
  MODIFY `test_config_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transaction_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

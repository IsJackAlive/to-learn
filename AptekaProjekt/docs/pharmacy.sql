-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 04 Gru 2021, 21:18
-- Wersja serwera: 10.4.21-MariaDB
-- Wersja PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pharmacy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Apteka`
--

CREATE TABLE `Apteka` (
  `id_apteka` int(11) NOT NULL,
  `adres` text COLLATE utf8mb4_polish_ci NOT NULL,
  `telefon` text COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `Apteka`
--

INSERT INTO `Apteka` (`id_apteka`, `adres`, `telefon`) VALUES
(1, '077 Sutherland Road', '633-530-8396'),
(2, '797 Jackson Plaza', '762-788-1862'),
(3, '48615 Amoth Park', '700-434-4170'),
(4, '85121 Arkansas Place', '769-442-9100'),
(5, '963 Miller Terrace', '220-845-6503');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Doktorzy`
--

CREATE TABLE `Doktorzy` (
  `id_doktor` int(11) NOT NULL,
  `imie` text COLLATE utf8mb4_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8mb4_polish_ci NOT NULL,
  `telefon` text COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `Doktorzy`
--

INSERT INTO `Doktorzy` (`id_doktor`, `imie`, `nazwisko`, `telefon`) VALUES
(1, 'Bryant', 'Fannon', '710-798-1252'),
(2, 'Nels', 'Downie', '616-380-3272'),
(3, 'Lauren', 'Mougel', '973-807-7240'),
(4, 'Stafani', 'Marchment', '691-939-5291'),
(5, 'Bordy', 'Dunnett', '815-447-5378'),
(6, 'Cherianne', 'Triebner', '189-191-2996'),
(7, 'Sukey', 'Croasdale', '204-635-7981'),
(8, 'Edith', 'Haslum', '766-630-8651');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Pacjenci`
--

CREATE TABLE `Pacjenci` (
  `id_pacjent` int(11) NOT NULL,
  `imie` text COLLATE utf8mb4_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8mb4_polish_ci NOT NULL,
  `telefon` text COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `Pacjenci`
--

INSERT INTO `Pacjenci` (`id_pacjent`, `imie`, `nazwisko`, `telefon`) VALUES
(1, 'Bobby', 'Plaistowe', '358-294-3010'),
(2, 'Ingrid', 'Gummary', '273-804-2869'),
(3, 'Micheline', 'Rowson', '846-396-7518'),
(4, 'Brittni', 'Kerne', '883-404-2230'),
(5, 'Netty', 'Fryatt', '917-858-2873'),
(6, 'Mariann', 'Feldmark', '497-726-9519'),
(7, 'Pebrook', 'Concannon', '637-739-1802'),
(8, 'Lincoln', 'Schwand', '551-326-7399'),
(9, 'Anette', 'Durtnall', '129-519-8299'),
(10, 'Martainn', 'Rhymes', '195-452-6092'),
(11, 'Francklin', 'Schroeder', '616-972-5803'),
(12, 'Brady', 'Beckensall', '195-481-8088'),
(13, 'Davon', 'Bowmen', '923-577-7430'),
(14, 'Consalve', 'Pilcher', '179-408-6218'),
(15, 'Glynda', 'Standidge', '186-824-9035'),
(16, 'Kingston', 'Couser', '113-336-7758'),
(17, 'Milzie', 'Juszczyk', '868-952-6105'),
(18, 'Thayne', 'Gerge', '243-577-1928'),
(19, 'Dwain', 'Pasque', '382-324-1181'),
(20, 'Quinn', 'de Almeida', '105-475-7630'),
(21, 'Thelma', 'Boothby', '725-660-1463'),
(22, 'Doralyn', 'Johl', '548-383-6525'),
(23, 'Lyell', 'Zupone', '948-753-2429'),
(24, 'Blondie', 'Farady', '639-457-7335'),
(25, 'Brande', 'Szymanowicz', '771-553-8676'),
(26, 'Cami', 'Graeser', '239-636-8230'),
(27, 'Bill', 'Killgus', '247-678-3605'),
(28, 'Winnifred', 'Kidner', '834-119-3953'),
(29, 'Peder', 'Georges', '958-885-9425'),
(30, 'Kent', 'Santello', '266-863-2310'),
(31, 'Chancey', 'Pace', '449-621-0849'),
(32, 'Carmencita', 'Stock', '695-702-3436'),
(33, 'Sheeree', 'Presho', '556-247-4781'),
(34, 'Keefer', 'Izac', '827-305-7725'),
(35, 'Hilde', 'Finlator', '896-769-1886'),
(36, 'Carolee', 'Rose', '745-593-9523'),
(37, 'Bel', 'Couling', '201-972-2816'),
(38, 'Darsie', 'Glasner', '214-938-2271'),
(39, 'Gwendolin', 'Nutting', '746-487-8541'),
(40, 'Juliette', 'Klulik', '474-438-2714'),
(41, 'Ashly', 'Sapwell', '922-235-7750'),
(42, 'Horst', 'Brickett', '499-317-4589'),
(43, 'Bertina', 'Colbridge', '754-774-0320'),
(44, 'Kizzie', 'Gianettini', '716-688-3415'),
(45, 'Riane', 'Maine', '798-253-6886'),
(46, 'Drona', 'Elbourne', '592-610-6892'),
(47, 'Stirling', 'Jikovsky', '175-489-4631'),
(48, 'Jacquelynn', 'Braney', '391-977-0039'),
(49, 'Cindy', 'Farrant', '921-486-3779'),
(50, 'Juliana', 'Merrgen', '339-162-0994'),
(51, 'Vanda', 'Benedek', '761-629-2565'),
(52, 'Frannie', 'Wyld', '496-638-5529'),
(53, 'Kitty', 'Sooper', '516-542-0151'),
(54, 'Stace', 'Dun', '830-743-3508'),
(55, 'Fairleigh', 'Mordie', '411-563-9553'),
(56, 'Fonz', 'Broadbear', '428-665-4418'),
(57, 'Eduino', 'Eddolls', '764-328-3379'),
(58, 'Catharina', 'Springford', '138-400-8389'),
(59, 'Griz', 'Timberlake', '286-420-3870'),
(60, 'Timotheus', 'Huge', '837-718-6047'),
(61, 'Katharine', 'Bursnall', '709-206-2393'),
(62, 'Emmeline', 'Beedham', '885-100-0671'),
(63, 'Barney', 'Sigsworth', '286-120-3853'),
(64, 'Adamo', 'Hampshaw', '979-106-3642'),
(65, 'Beckie', 'LAbbet', '734-172-0323'),
(66, 'Paulo', 'Iorizzo', '885-957-6882'),
(67, 'Upton', 'Glanton', '122-524-9686'),
(68, 'Gherardo', 'Litel', '288-374-8736'),
(69, 'Michaella', 'Portwaine', '303-287-9536'),
(70, 'Glory', 'Wickie', '891-379-6444'),
(71, 'Melamie', 'Daulby', '375-194-2791'),
(72, 'Robby', 'Fenelow', '567-701-3526'),
(73, 'Loree', 'Mouse', '328-485-1655'),
(74, 'Dacey', 'Broggio', '176-394-9303'),
(75, 'Maddie', 'Ambrosetti', '950-694-0029'),
(76, 'Leland', 'Cicculi', '602-697-6706'),
(77, 'Ashley', 'Pariss', '555-617-3220'),
(78, 'Ester', 'Giottini', '931-190-7896'),
(79, 'Tiphani', 'Paull', '951-114-2036'),
(80, 'Laureen', 'Duckett', '254-405-5274'),
(81, 'Kev', 'Galliard', '701-277-7715'),
(82, 'Shena', 'Wallis', '979-123-7799'),
(83, 'Byrle', 'Sousa', '241-273-5103'),
(84, 'Goldi', 'Fuxman', '155-679-3191'),
(85, 'Rafaellle', 'Muschette', '373-729-8363'),
(86, 'Audrey', 'Le Prevost', '540-538-7322'),
(87, 'Roldan', 'Korn', '108-856-9715'),
(88, 'Tawsha', 'Balmann', '228-564-5152'),
(89, 'Darnall', 'Gladden', '429-718-5834'),
(90, 'Doug', 'Pendrid', '347-454-6128'),
(91, 'Heda', 'McAvin', '348-672-8099'),
(92, 'Efrem', 'Gonnely', '818-316-4001'),
(93, 'Guthrie', 'Vere', '147-509-5198'),
(94, 'Audrey', 'Twelftree', '370-598-3094'),
(95, 'Beverly', 'McKeighen', '690-924-0608'),
(96, 'Nicolas', 'Ebbitt', '640-700-9092'),
(97, 'Candis', 'Farrey', '353-970-5683'),
(98, 'Taffy', 'Collins', '550-407-6851'),
(99, 'Thorstein', 'Miranda', '879-838-0421'),
(100, 'Lenard', 'Arrell', '308-785-2092');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Produkty`
--

CREATE TABLE `Produkty` (
  `id_produktu` int(11) NOT NULL,
  `id_apteka` int(11) NOT NULL,
  `nazwa` text COLLATE utf8mb4_polish_ci NOT NULL,
  `data_wydania` date NOT NULL,
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `Produkty`
--

INSERT INTO `Produkty` (`id_produktu`, `id_apteka`, `nazwa`, `data_wydania`, `cena`) VALUES
(1, 2, 'Lorazepam', '2021-10-22', 50),
(2, 5, 'Nighttime Multi-Symptom Cold and Flu Relief', '2021-03-29', 67),
(3, 1, 'Indomethacin', '2021-08-19', 68),
(4, 2, 'Corvert', '2020-12-03', 59),
(5, 3, 'Naproxen Kit', '2021-01-08', 134),
(6, 4, 'Anti-Bacterial Hand Spray', '2021-06-11', 156),
(7, 5, 'ARTHRITIS-EASE', '2021-01-20', 134),
(8, 2, 'Antibacterial Wet Wipes', '2021-03-12', 45),
(9, 1, 'SKELAXIN', '2021-06-03', 39),
(10, 2, 'Head and Shoulders', '2021-04-27', 265),
(11, 5, 'ZinClear SPF 30 Tinted', '2021-04-28', 240),
(12, 2, 'AMITRIPTYLINE HYDROCHLORIDE', '2021-07-19', 79),
(13, 5, 'Virginia Live Oak', '2021-06-22', 54),
(14, 2, 'AcipHex', '2021-01-29', 44),
(15, 5, 'in control Nicotine', '2021-04-14', 58),
(16, 2, 'ATORVASTATIN CALCIUM', '2021-04-28', 150),
(17, 5, 'Privine', '2021-10-14', 267),
(18, 4, 'Levetiracetam', '2021-07-18', 131),
(19, 1, 'ARTHROTEC', '2021-05-13', 207),
(20, 5, 'ADVAIR', '2021-10-08', 33),
(21, 5, 'Atenolol', '2021-06-01', 135),
(22, 4, 'Isosorbide Sublingual', '2021-10-29', 32),
(23, 1, 'Alcohol Prep Pad', '2021-11-30', 144),
(24, 1, 'CPD', '2021-09-19', 296),
(25, 4, 'Ranitidine', '2021-10-14', 268),
(26, 4, 'BIOTENE', '2021-06-30', 285),
(27, 4, 'Amiodarone Hydrochloride', '2021-03-26', 267),
(28, 1, 'Smart Sense Hemorrhoidal', '2021-02-21', 236),
(29, 5, 'Ursodiol', '2021-01-23', 49),
(30, 3, 'BABY SKIN', '2021-05-31', 297),
(31, 1, 'ANTI AGE STRESS', '2021-10-10', 35),
(32, 1, 'Infants Simethicone', '2020-12-09', 287),
(33, 3, 'Nobac Plus E3', '2021-09-23', 283),
(34, 2, 'ACETAMINOPHEN AND CODEINE PHOSPHATE', '2021-03-02', 271),
(35, 4, 'Sheer Defense Tinted Moisturizer Broad Spectrum SPF 15 M50', '2021-08-22', 48),
(36, 1, 'Xodol', '2021-04-26', 34),
(37, 1, 'Miconazole 1', '2021-10-31', 122),
(38, 3, 'Diurex', '2021-08-20', 232),
(39, 4, 'Grama Grass', '2020-12-19', 125),
(40, 1, 'Theophylline', '2021-08-24', 78),
(41, 3, 'PHACE BIOACTIVE CLARIFYING SERUM', '2021-07-24', 204),
(42, 1, 'Zolpidem Tartrate', '2021-11-18', 96),
(43, 3, 'LBEL', '2021-10-21', 109),
(44, 5, 'Citalopram', '2021-09-18', 272),
(45, 4, 'BICILLIN CR', '2021-07-16', 48),
(46, 5, 'RISPERDAL CONSTA', '2021-01-16', 164),
(47, 4, 'Amar Petroleum', '2021-04-11', 213),
(48, 3, 'Minoxidil', '2021-01-15', 125),
(49, 2, 'Relief-PE', '2021-09-24', 97),
(50, 1, 'Synalar', '2021-07-11', 262),
(51, 3, 'Ropinirole Hydrochloride', '2021-10-02', 175),
(52, 1, 'SEROQUEL', '2021-10-05', 114),
(53, 5, 'Hydrocodone Bitartrate and Acetaminophen', '2021-11-24', 249),
(54, 3, 'CLOBETASOL PROPIONATE', '2021-02-23', 137),
(55, 2, 'Midazolam hydrochloride', '2021-04-09', 266),
(56, 3, 'Isosorbide Dinitrate', '2021-08-29', 177),
(57, 3, 'healthy accents pain relief pm', '2021-01-16', 75),
(58, 3, 'OPANA', '2021-04-23', 130),
(59, 1, 'Quetiapine fumarate', '2021-04-20', 242),
(60, 5, 'Publix Alcohol', '2021-10-20', 59),
(61, 2, 'Cough and Cold Relief HBP', '2021-01-30', 101),
(62, 1, 'BIONA-VIT', '2021-06-05', 238),
(63, 2, 'Aromasin', '2021-02-20', 281),
(64, 1, 'Lovastatin', '2021-10-27', 265),
(65, 4, 'Dicyclomine Hydrochloride', '2021-05-23', 77),
(66, 4, 'Lidocaine Hydrochloride', '2021-08-11', 78),
(67, 4, 'Fenofibrate', '2021-04-14', 189),
(68, 2, 'CPD/ADSOL Red Cell Preservation Solution System (PL 2209)', '2021-07-24', 217),
(69, 1, 'Lactulose', '2021-06-13', 142),
(70, 5, 'Analgesic', '2021-09-29', 161),
(71, 2, 'Warfarin Sodium', '2021-04-02', 141),
(72, 1, 'EZ Nite Sleep', '2021-05-08', 264),
(73, 2, 'Cover Fx Blemish Treatment Concealer N Med-Deep', '2021-09-20', 126),
(74, 2, 'Cotz Face (For Lighter Skin Tones) SPF 40', '2021-04-01', 176),
(75, 3, 'Aminocaproic Acid', '2021-03-19', 26),
(76, 1, 'Amiloride Hydrochloride and Hydrochlorothiazide', '2021-08-08', 66),
(77, 2, 'Polyethylene Glycol (3350)', '2021-04-12', 178),
(78, 2, 'Venlafaxine Hydrochloride', '2021-08-05', 61),
(79, 2, 'Oxycodone and Acetaminophen', '2021-11-13', 222),
(80, 3, 'Original Antibacterial foaming hand', '2021-01-01', 146),
(81, 4, 'Cephalexin', '2021-05-31', 274),
(82, 5, 'losartan potassium', '2021-01-18', 49),
(83, 5, 'Povidone Iodine Scrub', '2021-06-03', 291),
(84, 4, 'Hepar Magnesium 4', '2021-01-12', 162),
(85, 5, 'Levalbuterol', '2021-07-13', 167),
(86, 5, 'Supress DM', '2021-01-15', 87),
(87, 3, 'Nighttime Sleep Aid', '2021-11-27', 155),
(88, 3, 'VIDAZA', '2021-07-29', 200),
(89, 2, 'ESIKA Silk Skin SPF 9', '2021-06-26', 201),
(90, 4, 'Nifedipine', '2021-02-02', 258),
(91, 4, 'Torsemide', '2021-09-20', 114),
(92, 2, 'GENOTROPIN', '2020-12-31', 126),
(93, 2, 'Chlorpromazine Hydrochloride', '2021-02-22', 223),
(94, 2, 'PhysiciansCare Antacid', '2021-05-17', 43),
(95, 2, 'Molds, Rusts and Smuts, Stemphylium botryosum', '2021-06-14', 233),
(96, 3, 'ISOPROPYL ALCOHOL ANTISEPTIC GERMICIDE', '2020-12-17', 227),
(97, 3, 'MEDROX', '2021-07-27', 131),
(98, 1, 'Aztreonam', '2021-07-20', 152),
(99, 4, 'Zaleplon', '2021-03-03', 213),
(100, 1, 'Pleo Spermus', '2021-05-03', 222);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Recepta`
--

CREATE TABLE `Recepta` (
  `id_recepta` int(11) NOT NULL,
  `id_doktor` int(11) NOT NULL,
  `id_pacjent` int(11) NOT NULL,
  `id_produkt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `Recepta`
--

INSERT INTO `Recepta` (`id_recepta`, `id_doktor`, `id_pacjent`, `id_produkt`) VALUES
(1, 3, 1, 53),
(2, 2, 2, 91),
(3, 6, 3, 87),
(4, 6, 4, 92),
(5, 1, 5, 81),
(6, 5, 6, 96),
(7, 5, 7, 84),
(8, 1, 8, 65),
(9, 5, 9, 66),
(10, 7, 10, 24),
(11, 7, 11, 83),
(12, 6, 12, 9),
(13, 8, 13, 10),
(14, 1, 14, 30),
(15, 8, 15, 43),
(16, 4, 16, 81),
(17, 8, 17, 26),
(18, 2, 18, 97),
(19, 7, 19, 27),
(20, 1, 20, 92),
(21, 3, 21, 99),
(22, 4, 22, 85),
(23, 1, 23, 80),
(24, 5, 24, 31),
(25, 4, 25, 82),
(26, 5, 26, 32),
(27, 4, 27, 49),
(28, 6, 28, 9),
(29, 8, 29, 31),
(30, 6, 30, 65),
(31, 1, 31, 91),
(32, 1, 32, 69),
(33, 6, 33, 67),
(34, 6, 34, 8),
(35, 3, 35, 19),
(36, 5, 36, 35),
(37, 4, 37, 47),
(38, 4, 38, 80),
(39, 6, 39, 15),
(40, 6, 40, 14),
(41, 2, 41, 19),
(42, 1, 42, 34),
(43, 2, 43, 56),
(44, 7, 44, 52),
(45, 4, 45, 69),
(46, 6, 46, 99),
(47, 4, 47, 78),
(48, 6, 48, 24),
(49, 5, 49, 76),
(50, 8, 50, 1),
(51, 3, 51, 93),
(52, 2, 52, 41),
(53, 6, 53, 91),
(54, 3, 54, 53),
(55, 7, 55, 62),
(56, 3, 56, 67),
(57, 6, 57, 79),
(58, 1, 58, 65),
(59, 8, 59, 49),
(60, 7, 60, 31),
(61, 1, 61, 88),
(62, 4, 62, 75),
(63, 3, 63, 23),
(64, 8, 64, 87),
(65, 2, 65, 93),
(66, 3, 66, 85),
(67, 6, 67, 29),
(68, 5, 68, 35),
(69, 4, 69, 62),
(70, 1, 70, 53),
(71, 3, 71, 35),
(72, 3, 72, 16),
(73, 2, 73, 85),
(74, 8, 74, 43),
(75, 7, 75, 72),
(76, 4, 76, 57),
(77, 2, 77, 86),
(78, 2, 78, 78),
(79, 3, 79, 50),
(80, 2, 80, 68),
(81, 2, 81, 65),
(82, 8, 82, 49),
(83, 3, 83, 100),
(84, 1, 84, 77),
(85, 3, 85, 17),
(86, 1, 86, 65),
(87, 4, 87, 11),
(88, 6, 88, 79),
(89, 5, 89, 100),
(90, 7, 90, 24),
(91, 2, 91, 38),
(92, 8, 92, 91),
(93, 4, 93, 39),
(94, 5, 94, 60),
(95, 7, 95, 79),
(96, 4, 96, 51),
(97, 2, 97, 39),
(98, 7, 98, 67),
(99, 3, 99, 28),
(100, 7, 100, 80);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `Apteka`
--
ALTER TABLE `Apteka`
  ADD PRIMARY KEY (`id_apteka`);

--
-- Indeksy dla tabeli `Doktorzy`
--
ALTER TABLE `Doktorzy`
  ADD PRIMARY KEY (`id_doktor`);

--
-- Indeksy dla tabeli `Pacjenci`
--
ALTER TABLE `Pacjenci`
  ADD PRIMARY KEY (`id_pacjent`);

--
-- Indeksy dla tabeli `Produkty`
--
ALTER TABLE `Produkty`
  ADD PRIMARY KEY (`id_produktu`),
  ADD KEY `id_apteka` (`id_apteka`);

--
-- Indeksy dla tabeli `Recepta`
--
ALTER TABLE `Recepta`
  ADD PRIMARY KEY (`id_recepta`),
  ADD KEY `id_doktor` (`id_doktor`),
  ADD KEY `id_pacjent` (`id_pacjent`),
  ADD KEY `id_produkt` (`id_produkt`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `Produkty`
--
ALTER TABLE `Produkty`
  ADD CONSTRAINT `Produkty_ibfk_1` FOREIGN KEY (`id_apteka`) REFERENCES `Apteka` (`id_apteka`),
  ADD CONSTRAINT `Produkty_ibfk_2` FOREIGN KEY (`id_produktu`) REFERENCES `Recepta` (`id_produkt`);

--
-- Ograniczenia dla tabeli `Recepta`
--
ALTER TABLE `Recepta`
  ADD CONSTRAINT `Recepta_ibfk_1` FOREIGN KEY (`id_pacjent`) REFERENCES `Pacjenci` (`id_pacjent`),
  ADD CONSTRAINT `Recepta_ibfk_2` FOREIGN KEY (`id_doktor`) REFERENCES `Doktorzy` (`id_doktor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

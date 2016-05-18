INSERT INTO `aktivnost` (`idAktivnosti`, `Naziv`, `BrojDjece`, `cijena`) VALUES (NULL, 'Engelski', '25', '10'), (NULL, 'Njemacki', '20', '15'), (NULL, 'Talijanski', '20', '15'), (NULL, 'Francuski', '20', '15');

INSERT INTO `grupa` (`idGrupe`, `naziv`, `RedniBroj`, `Kapacitet`) VALUES (NULL, 'Prva grupa', '1', '30'), (NULL, 'Druga grupa', '2', '30'), (NULL, 'Treca grupa', '3', '30'), (NULL, 'Cetvrta grupa', '4', '30');

INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Adnan', 'Kicin', '10-09-1992', 'Podmostovska bb', 'Hamzalija', '062403784', 'Kicin', '2016-05-11', '2016-10-26', 1);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Haris', 'Musovic', '11-01-1994', 'Podmostovska bb', 'Harisov Otac', '062403784', 'Musovic', '2016-05-11', '2016-10-26', 1);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Mujo', 'Mujic', '28-03-1997', 'Drinska bb', 'Suljo', '061223344', 'Mujic', '2016-05-15', '2017-05-15', 2);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Suljo', 'Suljic', '25-10-1997', 'Dz. Bijedica 12', 'Mujo', '061445566', 'Suljic', '2016-05-15', '2017-05-15', 2);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Hamo', 'Hamic', '02-03-1997', 'Drinska bb', 'Ramo', '061223344', 'Hamic', '2016-05-15', '2017-05-15', 3);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Ramo', 'Ramic', '05-09-1997', 'Dz. Bijedica 12', 'Hamo', '061445566', 'Ramic', '2016-05-15', '2017-05-15', 4);

INSERT INTO `korisnik` (`idKorisnika`, `Ime`, `Prezime`, `KorisnickoIme`, `Sifra`, `Privilegije`, `BrojTelefona`) VALUES (NULL, 'Lejla', 'Nukic', 'lnukic2', '135', 'blagajnik', '062222333'), (NULL, 'Adnan', 'Kicin', 'akicin1', '135', 'direktor', '062222333');
INSERT INTO `korisnik` (`idKorisnika`, `Ime`, `Prezime`, `KorisnickoIme`, `Sifra`, `Privilegije`, `BrojTelefona`) VALUES (NULL, 'Haris', 'Musovic', 'reha1', 'hn3', 'direktor', '061614279');

INSERT INTO `termin` (`idTermin`, `idAktivnosti`, `idGrupe`, `vrijemePocetka`, `vrijemeZavrsetka`, `dan`) VALUES (NULL, '1', '2', '12:00', '13:00', 'utorak'), (NULL, '2', '1', '13:00', '14:00', 'srijeda'), (NULL, '3', '1', '13:00', '14:00', 'utorak');

INSERT INTO `uplata` (`idUplate`, `DatumUplate`, `VisinaUplate`, `idDijete`, `zaMjesec`, `zaGodinu`) VALUES (NULL, '2016-05-11', '200', '1', '5', '2015');
INSERT INTO `vaspitac` (`idVaspitac`, `Ime`, `Prezime`, `BrojTelefona`, `AdresaPrebivalista`, `idGrupe`) VALUES (NULL, 'Dzenana', 'Kapetanovic', '061123333', 'mihrivode 134', '1'), (NULL, 'Ramiza', 'Ramizic', '061123333', 'mihrivode 134', '2'), (NULL, 'Hamida', 'Hamidic', '061123333', 'mihrivode 134', '2'), (NULL, 'Hamdo', 'Hamdic', '061123333', 'mihrivode 134', '3'), (NULL, 'Ramo', 'Ramic', '061123333', 'mihrivode 134', NULL);
INSERT INTO `zaduzenja` (`idZaduzenja`, `idDijete`, `mjesec`, `godina`) VALUES (NULL, '1', 'Januar', '2016'), (NULL, '1', 'Februar', '2016'), (NULL, '1', 'Mart', '2016'), (NULL, '1', 'April', '2016'); 

INSERT INTO `tim9`.`aktivnostidjeca` (`idDijete`, `idAktivnosti`) VALUES ('1', '1'), ('1', '2'), ('2', '1'), ('2', '2');


-- Za login kao Direktor koristiti username:akicin1 i password:123
-- za login kao Blagajnik koristiti username:lnukic2 i password:123
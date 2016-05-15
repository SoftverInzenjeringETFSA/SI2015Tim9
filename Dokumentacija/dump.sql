INSERT INTO `aktivnost` (`idAktivnosti`, `Naziv`, `BrojDjece`, `cijena`) VALUES (NULL, 'Engelski', '25', '10'), (NULL, 'Talijanski', '20', '15');
INSERT INTO `grupa` (`idGrupe`, `naziv`, `RedniBroj`, `Kapacitet`) VALUES (NULL, 'Prva grupa', '1', '30'), (NULL, 'Druga', '2', '30');
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Adnan', 'Kičin', '1992-09-10', 'Podmostovska bb', 'Hamzalija', '062403784', 'Kicin', '2016-05-11', '2016-10-26', 1);
INSERT INTO `dijete` (`idDijete`, `Ime`, `Prezime`, `DatumRodjenja`, `AdresaPrebivalista`, `ImeRoditelja`, `BrojTelefona`, `PrezimeRoditelja`, `DatumUpisa`, `DatumIsteka`, `idGrupe`) VALUES (NULL, 'Haris', 'Mušović', '1992-09-10', 'Podmostovska bb', 'Harisov Otac', '062403784', 'Mušović', '2016-05-11', '2016-10-26', 1);
INSERT INTO `korisnik` (`idKorisnika`, `Ime`, `Prezime`, `KorisnickoIme`, `Sifra`, `Privilegije`, `BrojTelefona`) VALUES (NULL, 'Lejla', 'Nukić', 'lnukic2', '135', 'blagajnik', '062222333');
INSERT INTO `korisnik` (`idKorisnika`, `Ime`, `Prezime`, `KorisnickoIme`, `Sifra`, `Privilegije`, `BrojTelefona`) VALUES (NULL, 'Adnan', 'Kičin', 'akicin1', '135', 'direktor', '062222333');
INSERT INTO `termin` (`idTermin`, `idAktivnosti`, `idGrupe`, `vrijemePocetka`, `vrijemeZavrsetka`, `dan`) VALUES (NULL, '1', '2', '12:00', '13:00', 'utorak');
INSERT INTO `uplata` (`idUplate`, `DatumUplate`, `VisinaUplate`, `idDijete`, `zaMjesec`, `zaGodinu`) VALUES (NULL, '2016-05-11', '200', '1', '5', '2015');
INSERT INTO `vaspitac` (`idVaspitac`, `Ime`, `Prezime`, `BrojTelefona`, `AdresaPrebivalista`, `idGrupe`) VALUES (NULL, 'Dzenana', 'Kapetanovic', '061123333', 'mihrivode 134', '1');
INSERT INTO `zaduzenja` (`idZaduzenja`, `idDijete`, `mjesec`, `godina`) VALUES (NULL, '1', 'juni', '2014'); 
-- Za login kao Direktor koristiti username:akicin1 i password:123
-- za login kao Blagajnik koristiti username:lnukic2 i password:123
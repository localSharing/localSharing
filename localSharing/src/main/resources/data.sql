--Benutzer
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('1', 'heinrich.braun@dh.de', 'TRUE', '0', '42', 'Baumeister', '$2a$10$W9XmSD8ZNTh3WsjrwEfzcu7V5epyCIJkvTWFEIgLHoyNsoYfU4Hdy', '11115', 'Heidelberg', 'Braunscher Weg', '0123451232', 'Heinrich');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('2', 'peter.pan@nimmerland.de', 'TRUE', '0', '42', 'Braun', '$2a$10$36uFyCT3/NC7gv4Lj0QlBOTZCHIoT9ZdYARBEbE0WFoTYAKqmlxkG', '12345', 'Heidelberg', 'Braunscher Weg', '0123451232', 'Heinrich');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('3', 'tester@cucumber.de', 'TRUE', '0', '11', 'Blau', '$2a$10$pbsJj1OfRj5HkEALDOlg1uVDB5xULjBEKgTS11Qcn7.tX2XHzuzl2', '12345', 'Karlsruhe', 'Erzbergerstraße', '0123451232', 'Peter');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('4', 'cookie@monster.com', 'TRUE', '0', '12', 'Monster', '$2a$10$pbsJj1OfRj5HkEALDOlg1uVDB5xULjBEKgTS11Qcn7.tX2XHzuzl2', '54321', 'Karlsruhe', 'Sesamstraße', '0123451232', 'Cookie');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('5', 'simba@thelionking.com', 'TRUE', '0', '1', 'Lion-King', '$2a$10$pbsJj1OfRj5HkEALDOlg1uVDB5xULjBEKgTS11Qcn7.tX2XHzuzl2', '00000', 'Savanne', 'Pride Rock', '0123451232', 'Simba');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('6', 'barack.obama@whitehouse.com', 'TRUE', '0', '1600', 'Obama', '$2a$10$pbsJj1OfRj5HkEALDOlg1uVDB5xULjBEKgTS11Qcn7.tX2XHzuzl2', '20500', 'Washington DC', 'Pennsylvania Avenue NW', '202-456-1111', 'Barack');
insert into PUBLIC.BENUTZER (USERID, EMAIL, ENABLED, GESCHLECHT, HAUSNR, NACHNAME, PASSWORT, PLZ, STADT, STRASSE, TELNR, VORNAME) values ('7', 'angela.merkel@bundeskanzleramt.de', 'TRUE', '1', '1', 'Merkel', '$2a$10$pbsJj1OfRj5HkEALDOlg1uVDB5xULjBEKgTS11Qcn7.tX2XHzuzl2', '10557', 'Berlin', 'Willy-Brandt-Straße', '030182722720', 'Angela');
--Ausleihartikel
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('1', 'Ein toller Film. DVD ist in gutem Zustand.', '30.11.2014', 'Tarzan', '4', '5', '30.11.2015', 'DVD');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('2', 'Schönes Buch, in gutem Zustand.', '01.12.2014', 'The Fault in Our Stars', '7', '14', '31.03.2015', 'Buch');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('3', 'OLAF :DD', '01.12.2014', 'Frozen', '5', '8', '01.01.2016', 'DVD');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('4', 'Bierbänke und -tische sind in gutem Zustand. Auch für größere Feste geeignet - bis zu 50 Personen finden Platz. Wenn ich sie selbst brauche, kann ich sie logischerweise nicht verleihen, aber ansonsten seid ihr alle herzlich dazu eingeladen, sie auch zu benutzen, sofern ihr gut darauf aufpasst ;) Anfragen bitte mindestens 3 Tage im Voraus, damit wir gemeinsam die "Übergabe" klären können. Am liebsten ist es mir, wenn ihr alle bei mir zu Hause abholt, aber auch darüber können wir gerne sprechen. Viel Spaß mit den Bänken und Tischen :)', '03.12.2014', 'Bierbänke und -tische', '7', '8', '31.03.2015', 'Garten');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('5', 'Werkzeugkasten mit allerlei Werkzeugen, die man so zum "Hobbyheimwerkern" braucht. Alles in gutem Zustand. Auf dem Bild sollten die meisten Werkzeuge zu erkennen sein - falls ihr was spezielles braucht, schreibt das bitte in die Anfrage rein, dann kann ich sehen, was ich für euch tun kann :)', '2014-12-03', 'Werkzeugkasten', '3', '14', '2015-03-31', 'Handwerk');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('6', 'An awesome book which turned my life upside down! If you wanna be the next POTUS, do not miss out on this book. In fact, if you have got this book, there is no way you won`t be at least a candidate in the next election. It contains a lot of practical help and is a great guide for all of you politicians out there. I wish you the best of luck with it!! You can borrow it for 100 days. Contact me if it worked out for you and you wish to keep it longer - I know how exhausting and time-consuming the electoral campaign can be!', '03.12.2014', 'How do I become the next president of the USA?', '6', '100', '01.01.2016', 'Buch');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('7', 'OLAF :DD', '2014-12-03', 'Holzschlitten', '5', '14', '2017-11-01', 'Wintersport');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('8', 'Die Skiier (das andere Paar findet ihr unter "Angelas Angebote") sind in einem guten Zustand. Dieses Paar hat einen kleinen Kratzer abbekommen, nachdem ich gestürzt bin - zum Glück nichts passiert! - aber das beeinträchtigt den Fahrspaß in keinster Weise. Joachim und ich sind beide berufstätig und haben deshalb leider nicht so viel Zeit für den Skilanglauf wie wir es uns wünschen würden. Deshalb freuen wir uns aber, wenn jemand anderes im Urlaub Ski gebrauchen kann und wir helfen können :) Viel Spaß im Urlaub euch! ', '03.12.2014', 'Ski (Langlauf)', '7', '14', '30.04.2016', 'Wintersport');
insert into PUBLIC.AUSLEIHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, DAUER, ENDDATUM, KATEGORIE) values ('9', 'DVDs sind in super Zustand. Tolle Serie, die ich gerne mit euch teile :)', '04.12.2014', 'Das Traumschiff DVD-Box 4', '4', '10', '31.07.2015', 'DVD');
--Hilfelesitungen
insert into PUBLIC.HILFELEISTUNG (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, ENDDATUM) values ('1', 'Ich bin gerne behilflich, wenn Streicharbeiten anfallen (und ich ein Stück Kuchen bekomme).', '02.12.2014', 'Streichen', '3', '02.12.2015');
insert into PUBLIC.HILFELEISTUNG (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, ENDDATUM) values ('2', 'Ich bin gerne behilflich bei Fragen zu Algorithmen, vor allem theoretischer Natur. Lassen Sie mich ihr O-Kalkül mithilfe der Induktion berechnen!', '02.12.2014', 'Theoretische Informatik', '1', '30.11.2014');
insert into PUBLIC.HILFELEISTUNG (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, ENDDATUM) values ('3', 'Ich bin nett und gehe gerne für meine Mitmenschen einkaufen, wenn ich sowieso schon dabei bin. Bitte rechtzeitig anfragen - ich gehe weder für 5 Leute gleichzeitig einkaufen noch für ein großes Fest. Ansonsten helfe ich gerne :D', '02.12.2014', 'Einkaufen', '5', '02.12.2015');
insert into PUBLIC.HILFELEISTUNG (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, ENDDATUM) values ('4', 'If you need any help regarding "Obamacare" just get in touch with me. I will do my best at helping you out!', '02.12.2014', 'Obamacare FAQ', '6', '02.12.2015');
--Tauschartikel
insert into PUBLIC.TAUSCHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, KATEGORIE) values ('1', 'Geschirrset (Teller, Tassen, Schüsseln) von 1880. Zeigt Gebrauchsspuren, sonst guter Zustand. Für weitere Infos, bitte kontaktieren.', '29.11.2014', 'Geschirrset', '7', 'Küche');
insert into PUBLIC.TAUSCHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, KATEGORIE) values ('2', 'Stehlampe der Firma ShineDesign. Modernes Design, Lampenschirm aus Holz.', '05.12.2014', 'Stehlampe', '4', 'Wohnen');
insert into PUBLIC.TAUSCHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, KATEGORIE) values ('3', 'Kalender von 2011. Mottokalender "Natur". Tolle Bilder, auch 2015.', '05.12.2014', 'Kalender (2011, Natur)', '1', 'Dekoration');
insert into PUBLIC.TAUSCHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, KATEGORIE) values ('4', 'Ein paar Larven, von Timon und Pumba gefangen. Seeehr lecker. Hakuna Matata!', '06.12.2014', 'Larven', '5', 'Essen');
insert into PUBLIC.TAUSCHARTIKEL (ANGEBOTSID, BESCHREIBUNG, STARTDATUM, TITEL, BENUTZER_USERID, KATEGORIE) values ('5', 'MarcoPolo-Reiseführer für Rom (8. Auflage, 2013). Tausche gerne gegen andere Reiseführer :)', '07.12.2014', 'Rom: Marco-Polo-Reiseführer', '7', 'Reisen');
commit;
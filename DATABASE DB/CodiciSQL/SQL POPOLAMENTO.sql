--popolo la tabella SEDE:
INSERT INTO SEDE(NomeSede,NomeVia,NumeroCivico,Città)
VALUES('convitto palmieri','piazzetta carducci',28,'lecce');

INSERT INTO SEDE(NomeSede,NomeVia,NumeroCivico,Città)
VALUES('monte sant''angelo','via cintia',21,'napoli');

INSERT INTO SEDE(NomeSede,NomeVia,NumeroCivico,Città)
VALUES('porta di massa', 'via porta di massa',1, 'napoli');





--popolo la tabella LOCAZIONE:
INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Athena','monte sant''angelo');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Nafsika','monte sant''angelo');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Sala d''arte (First floor)','convitto palmieri');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Sala Museo della Stampa','convitto palmieri');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Teatrino','convitto palmieri');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Sala sul Chiostro del 500 (First floor)','convitto palmieri');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Chiostro del 500','convitto palmieri');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('AulaP','porta di massa');

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Sala Grande','porta di massa');





--popolo la tabella CONFERENZA:
INSERT INTO CONFERENZA(TitoloConferenza,DataInizio,DataFine,Descrizione,NomeSede)
VALUES('21st International Conference on Image Analysis and Processing', '23/05/2022','27/05/2022',
	  'ICIAP: 21st International Conference on image analysis and processing 24 May 2022
	   Vi aspettiamo a ICIAP, la Conferenza organizzata ogni due anni dall’Association for
	   Research in Computer Vision, Pattern Recognition and Machine Learning (CVPL, ex
	   GIRPR) che è parte dell’International Association for Pattern Recognition (IAPR).','convitto palmieri');
	   
INSERT INTO CONFERENZA(TitoloConferenza,DataInizio,DataFine,Descrizione,NomeSede)
VALUES('IEEE CSR CyberSecurity and Resilience', '27/07/2022','29/07/2022',
	  'The IEEE International Conference on Cyber Security and Resilience recognizes outstanding individuals
	   who make substantial contributions to the advancement of
	   security and resilience, inspiring other members of the community with their pioneering
	   research and innovation. The awardees need not be IEEE members.','monte sant''angelo');





--popolo la tabella SPONSOR:
INSERT INTO SPONSOR(PartitaIVA,NomeAzienda)
VALUES('04935230963','Apple Retail Italia S.r.l.');

INSERT INTO SPONSOR(PartitaIVA,NomeAzienda)
VALUES('11325690151','Samsung Electronics Italia S.p.A.');

INSERT INTO SPONSOR(PartitaIVA,NomeAzienda)
VALUES('00348270133','META S.P.A.');





--popolo la tabella PROGRAMMA:
INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('23/05/2022',1);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('24/05/2022',1);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('25/05/2022',1);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('26/05/2022',1);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('27/05/2022',1);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('27/07/2022',2);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('28/07/2022',2);

INSERT INTO PROGRAMMA(DataProgramma,CodConferenza)
VALUES('29/07/2022',2);





--popolo la tabella INTERVALLO:
INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak', '10:30:00','11:00:00',0);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','15:30:00','16:00:00',0);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','10:30:00','11:00:00',1);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:00:00','14:00:00',1);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','15:30:00','16:00:00',1);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','10:30:00','11:00:00',2);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:00:00','14:00:00',2);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','10:30:00','11:00:00',3);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:00:00','14:00:00',3);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','15:30:00','16:00:00',3);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','10:30:00','11:00:00',4);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:00:00','14:00:00',4);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','15:30:00','16:00:00',4);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','11:40:00','12:00:00',5);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:20:00','14:20:00',5);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','16:00:00','16:20:00',5);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','11:40:00','12:00:00',6);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:20:00','14:20:00',6);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','18:00:00','18:20:00',6);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('CoffeBreak','11:40:00','12:00:00',7);

INSERT INTO INTERVALLO(TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)
VALUES('Pranzo','13:20:00','14:20:00',7);





--popolo la tabella EVENTO_SOCIALE:
INSERT INTO EVENTO_SOCIALE(TipoEvento,OrarioInizioEvento,OrarioFineEvento,CodProgramma)
VALUES('Cena','19:30:00','22:00:00',2);

INSERT INTO EVENTO_SOCIALE(TipoEvento,OrarioInizioEvento,OrarioFineEvento,CodProgramma)
VALUES('Cena','18:50:00','23:30:00',3);

INSERT INTO EVENTO_SOCIALE(TipoEvento,OrarioInizioEvento,OrarioFineEvento,CodProgramma)
VALUES('Gita','18:50:00','23:30:00',4);





--popolo la tabella ENTE:
INSERT INTO ENTE(NomeIstituzione)
VALUES('Università degli Studi di Napoli Federico II');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università Parthenope');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università del Salento');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università Vanvitelli');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università degli Studi di Salerno');

INSERT INTO ENTE(NomeIstituzione)
VALUES('University of Cambridge');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università di Pavia');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Bournemouth University');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Humanitas University');

INSERT INTO ENTE(NomeIstituzione)
VALUES('University of Zilina');

INSERT INTO ENTE(NomeIstituzione)
VALUES('University of Alicante');

INSERT INTO ENTE(NomeIstituzione)
VALUES('Università di Catania');





--popolo la tabella ORGANIZZATORE_LOCALE:
INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('CarmenBisogni@unisa.it','Mss', 'Carmen','Bisogni', 'Università degli Studi di Salerno');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('PiercarloDondi@unipa.it','Professor', 'Piercarlo','Dondi', 'Università di Pavia');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('FabioNarducci@unisa.it','Mr', 'Fabio','Narducci', 'Università degli Studi di Salerno');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('AlessandroBruno@gmail.it','Mr', 'Alessandro','Bruno', 'Humanitas University');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('SimonePalazzo@unica.it','Mr', 'Simone','Palazzo', 'Università di Catania');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('FedericaProiettoSalanitri@unisa.it','Mss', 'Federica','Proietto Salanitri', 'Università di Catania');

INSERT INTO ORGANIZZATORE_LOCALE(emailL,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('VincenzoLomonaco@unipa.it','Professor', 'Vincenzo','Lomonaco', 'Università di Pavia');






--popolo la tabella ORGANIZZATORE_SCIENTIFICO:
INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('ZoheirSabeur@gmail.com','Professor','Zoheir','Sabeur','Bournemouth University');

INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('DenizChetinkaya@gmail.com','Professoressa', 'Deniz','Chetinkaya', 'Bournemouth University');

INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('FranciscoFlorez-Revuelta@gmail.com','Mr', 'Francisco','Florez-Revuelta', 'University of Alicante');

INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('PeterPocta@gmail.com','Mr', 'Peter','Pocta', 'University of Zilina');

INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('JonAnderGomezAdrian@gmail.com','Mr', 'Jon Ander','Gomez Adrian', 'University of Alicante');

INSERT INTO ORGANIZZATORE_SCIENTIFICO(emailS,Titolo,Nome,Cognome,Istituzione_di_Afferenza)
VALUES('StephenHawking@gmail.com','Mr', 'Stephen','Hawking', 'University of Cambridge');






--popolo la tabella PARTECIPANTE:
INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('NicolaLerme@unipa.it','Mr','Nicola','Lerme','Università di Pavia');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('LorenzoBaraldi@unina.it','Mr','Lorenzo', 'Baraldi', 'Università degli Studi di Napoli Federico II');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('AlessandroGrieco@unina.it','Mr','Alessandro','Grieco','Università degli Studi di Napoli Federico II');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('StefanoAllegretti@unipart.it','Mr','Stefano','Allegretti','Università Parthenope');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('DarioMoccia@unipart.it','Professor','Dario', 'Moccia','Università Parthenope');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('ClaudioFerrari@unica.it','Professor','Claudio','Ferrari','Università di Catania');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('FedericoBeccaria@univa.it','Mr','Federico','Beccaria','Università Vanvitelli');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('AndreaPilzer@univa.it','Professor','Andrea','Pizler','Università Vanvitelli');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('GiovanniRana@gmail.com','Professor','Giovanni','Rana','Bournemouth University');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('CosimoDistante@unisal.it','Professor','Cosimo','Distante','Università del Salento');

INSERT INTO PARTECIPANTE(emailP, Titolo, Nome, Cognome, Istituzione_di_Afferenza)
VALUES('FrancescoBarecchia@unina.it','Mr','Francesco','Barecchia','Università degli Studi di Napoli Federico II');





--popolo la tabella ponte ORGANIZZARE_S:
INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'FranciscoFlorez-Revuelta@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'DenizChetinkaya@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'JonAnderGomezAdrian@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'PeterPocta@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'ZoheirSabeur@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(1,'StephenHawking@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(2,'FranciscoFlorez-Revuelta@gmail.com');

INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)
VALUES(2,'DenizChetinkaya@gmail.com');





--popolo la tabella SESSIONE:
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('9:00:00','10:30:00','Parts can worth like the whole','Quite often the useful data for an analysis task are not available in an optimal condition.
	   This may be due to the occlusions or the noise affecting the acquisition of the samples, or in some cases the problem itself is conceived in a way 
	   that a solution comes from the analysis of smaller portions of the input.', 'FranciscoFlorez-Revuelta@gmail.com','NicolaLerme@unipa.it',0,'Sala sul Chiostro del 500 (First floor)');
	   
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('11:00','15:30','HBAxSCES - Human Behaviour Analysis for Smart City Environment Safety','Nowadays, Smart Cities aim to ensure secure and safe physical and digital environments for the well-being of citizens.
	   Among many, ICT systems are reliant on evolving Artificial Intelligence, Pattern Recognition, Computer Vision,
	   3D simulations and Digital Twins techniques to make the environments more resilient.','JonAnderGomezAdrian@gmail.com','GiovanniRana@gmail.com',0,'Teatrino');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,CodProgramma,NomeLocazione)
VALUES('16:00','18:00','Deep Learning High Performance Computing to Boost Biomedical Applications','Healthcare is one of the key sectors of the global economy, especially in Europe. Any improvement in healthcare 
	   systems has a high impact on the welfare of the society.
	   The use of technologies in health is clearly a strong path to more efficient healthcare, benefitting both individual people and the publicbudgets. 
	   European public health systems are generating large datasets of biomedical data in general, and images in particular, as most medical examinations use image-based processes.',
	   'PeterPocta@gmail.com',0,'Sala d''arte (First floor)');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('9:00','10:30','Deep Learning for Visual Object Tracking Pt1','In its simplest definition, visual object tracking consists in the persistent recognition and localization of a generic 
	   target object in a video. Several challenges such as object occlusions, pose and scale changes, rotations and shape variations, and the presence of similar objects, must be tackled to accurately keep 
	   track of a target’s position. The ultimate goal of generic object tracking is to build robust models capable to overcome such challenging factors. In the past, such issues have been addressed by 
	   disparate principles formalizing the concepts of appearance model, motion model, and matching operation. In recent years, algorithms based on deep learning tried to
	   learn such conceptual blocks by exploiting the ability of deep neural networks in learning complex functions from visual examples.','ZoheirSabeur@gmail.com','AlessandroGrieco@unina.it',1,'Teatrino');
	   
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('11:00','13:00','Deep Learning for Visual Object Tracking Pt2','In its simplest definition, visual object tracking consists in the persistent recognition and localization of a generic 
	   target object in a video. Several challenges such as object occlusions, pose and scale changes, rotations and shape variations, and the presence of similar objects, must be tackled to accurately keep 
	   track of a target’s position. The ultimate goal of generic object tracking is to build robust models capable to overcome such challenging factors. In the past, such issues have been addressed by 
	   disparate principles formalizing the concepts of appearance model, motion model, and matching operation. In recent years, algorithms based on deep learning tried to
	   learn such conceptual blocks by exploiting the ability of deep neural networks in learning complex functions from visual examples.','ZoheirSabeur@gmail.com','AlessandroGrieco@unina.it',1,'Teatrino');	   

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,CodProgramma,NomeLocazione)
VALUES('11:00','13:00',' WOSDETC - Small-Drone Surveillance, Detection and Counteraction Techniques','In the last few years the popularity of small Remotely Piloted Aircraft Systems (RPAS) and more generally 
	   (also autonomous) “drones”, has exponentially increased due to the availability of low-cost off-the-shelf products, including build-from-scratch and DIY kits. At the same time, issues regarding safety, 
	   privacy and security aspects are arising. There is inded a gap in current surveillance systems for the detection of such flying systems, 
	   which can be used for illegal activities such as smuggling of drugs or espionage, as well as for carrying explosives or chemical weapons. 
	   ','PeterPocta@gmail.com',1,'Sala sul Chiostro del 500 (First floor)');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('14:00','15:30','DEEP LEARNING FOR MULTI-GPUS','Deep Learning has been the most significant breakthrough in the past 10 years in the field of pattern recognition and machine learning. It has achieved 
	   significant advancements in terms of the effectiveness of prediction models on many research topics and application fields, ranging from computer vision, natural language processing, embodied AI and to 
	   more traditional fields of pattern recognition. This paradigm shift has radically changed the research methodology towards a data-oriented approach, 
	   in which learning involves all steps of the prediction pipeline from feature extraction to classification.','JonAnderGomezAdrian@gmail.com','FrancescoBarecchia@unina.it',1,'Teatrino');
	   
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('8:30','10:30','Oral Session 1: Image Analysis, Detection and Recognition','ZoheirSabeur@gmail.com',2,'Sala d''arte (First floor)');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:00','15:30','Message from the General Chair','DenizChetinkaya@gmail.com',2,'Teatrino');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('11:00','13:00','Industrial Panel','The Industrial Panel session will start with presentations by the panelists, introducing the companies and the technological challenges of their business.',
		'PeterPocta@gmail.com','CosimoDistante@unisal.it',3,'Sala Museo della Stampa');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:00','15:30','Shifting paradigms in multi-object tracking','FranciscoFlorez-Revuelta@gmail.com',3,'Sala Museo della Stampa');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('11:00','13:00','Video Understanding - An Egocentric Perspective','This talk aims to argue for a fine(r)-grained perspective onto human-object interactions, from video sequences, captured 
 in an egocentric perspective (i.e. first-person footage).', 'FranciscoFlorez-Revuelta@gmail.com','NicolaLerme@unipa.it',4,'Sala d''arte (First floor)');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:00','15:30','Closing remarks','StephenHawking@gmail.com',4,'Sala Museo della Stampa');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('9:00','10:00','Plenary session PL-1','FranciscoFlorez-Revuelta@gmail.com',5,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','CSR-1','FranciscoFlorez-Revuelta@gmail.com',5,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('12:00','13:20','CSR-2','FranciscoFlorez-Revuelta@gmail.com',5,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:20','16:00','CSR-3','FranciscoFlorez-Revuelta@gmail.com',5,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('16:20','18:00','CSR-2','FranciscoFlorez-Revuelta@gmail.com',5,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','WS-DS4CS-1','DenizChetinkaya@gmail.com',5,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('12:00','13:20','CSR-2','DenizChetinkaya@gmail.com',5,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('15:00','16:00','CSR-3','DenizChetinkaya@gmail.com',5,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('16:20','18:00','CSR-2','DenizChetinkaya@gmail.com',5,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('9:00','10:00','PL-2','FranciscoFlorez-Revuelta@gmail.com',6,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','CSR-5','FranciscoFlorez-Revuelta@gmail.com',6,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('12:00','13:20','CSR-6','FranciscoFlorez-Revuelta@gmail.com',6,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:20','15:20','PL-3','FranciscoFlorez-Revuelta@gmail.com',6,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('15:20','16:20','AW','FranciscoFlorez-Revuelta@gmail.com',6,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','WS-CRE-1','DenizChetinkaya@gmail.com',6,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('12:00','13:20','WS-CRST','DenizChetinkaya@gmail.com',6,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('16:20','18:00','WS-CRE-2','DenizChetinkaya@gmail.com',6,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('18:40','20:00','WS-CRE-3','DenizChetinkaya@gmail.com',6,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('9:00','10:00','PL-4','FranciscoFlorez-Revuelta@gmail.com',7,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','CSR-7','FranciscoFlorez-Revuelta@gmail.com',7,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('12:00','13:20','CSR-8','FranciscoFlorez-Revuelta@gmail.com',7,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:20','16:00','CSR-9','FranciscoFlorez-Revuelta@gmail.com',7,'Athena');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('10:00','11:40','WS-EPES-SPR','DenizChetinkaya@gmail.com',7,'Nafsika');

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('14:20','16:00','WS-ACTI','DenizChetinkaya@gmail.com',7,'Nafsika');






--popolo la tabella INTERVENTO:
INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('AlessandroGrieco@unina.it','9:30','10:00',3);

INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('LorenzoBaraldi@unina.it','15:00','16:00',16);

INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('CosimoDistante@unisal.it','15:00','15:30',20);

INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('FrancescoBarecchia@unina.it','10:00','10:20',23);

INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('AlessandroGrieco@unina.it','10:20','10:40',23);

INSERT INTO INTERVENTO(codPartecipante,OrarioInizioIntervento,OrarioFineIntervento,CodSessione)
VALUES('ClaudioFerrari@unica.it','13:00','13:20',15);





--popolo la tabella ponte ORGANIZZARE_L:
INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(1,'CarmenBisogni@unisa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(1,'FabioNarducci@unisa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(1,'FedericaProiettoSalanitri@unisa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(1,'SimonePalazzo@unica.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(2,'CarmenBisogni@unisa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(2,'FabioNarducci@unisa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(2,'AlessandroBruno@gmail.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(2,'PiercarloDondi@unipa.it');

INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)
VALUES(2,'VincenzoLomonaco@unipa.it');





--popolo la tabella ponte PARTECIPARE:
INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(15,'NicolaLerme@unipa.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(15,'ClaudioFerrari@unica.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(16,'ClaudioFerrari@unica.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(16,'LorenzoBaraldi@unina.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(11,'ClaudioFerrari@unica.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(13,'LorenzoBaraldi@unina.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(4,'GiovanniRana@gmail.com');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(7,'GiovanniRana@gmail.com');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(5,'CosimoDistante@unisal.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(20,'CosimoDistante@unisal.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(23,'FrancescoBarecchia@unina.it');

INSERT INTO PARTECIPARE(CodSessione,emailP)
VALUES(23,'AlessandroGrieco@unina.it');





--popolo la tabella ponte PUBBLICITA':
INSERT INTO pubblicità(PartitaIVA, CodConferenza, Spesa)
VALUES('00348270133', 2, 200000);

INSERT INTO pubblicità(PartitaIVA, CodConferenza, Spesa)
VALUES('04935230963', 1, 150000);

INSERT INTO pubblicità(PartitaIVA, CodConferenza, Spesa)
VALUES('11325690151', 1, 75000);
 ### Suunnitelma
 
 Robotti tutkii ympäröivää maailmaansa ja reagoi kohtaamiinsa objekteihin tunnistamiensa värien perusteella. Esim. punaisen esteen kohdalla robotti voisi koittaa hyökätä, kun taas vihreää seurata ja keltaista kartettaa tms. Robotti pyrkii lähestymään aina lähintä löytämäänsä kohdetta.
 
 Värien tunnistus tapahtuu värisensorin avulla ja kohteen etsiminen infrapunasensorin etäisyysmittausten perusteella. Lähin kohde paikannetaan pyöräyttämällä robotti ympäri mittaamalla samalla kaikki etäisyydet ja palaamalla niistä lyhimpään. Tunnistettavat objektit voidaan rakentaa  esim. eri värisistä lego palikoista.
 
 Ongelmia voi tulla värien tunnistuksen kanssa, jos robotti ei pysty tunnistamaan värejä kunnolla (kuten ensimmäisten testien kanssa sekoitti esim. keltaiset ja vihreät legot keskenään ja luuli joitain värejä mustiksi, jos sensori oli liian lähellä palikkaa) tai tunnistus ei ole muuten riittävän täsmällistä.
 
 Ongelma voi olla myös lähimmän kohteen haussa niin ettei robotti keskity vain yhteen ja samaan kohteeseen tai jää jumiin seinään. Toimintojen määrät rajoittuu siihen mitä kolmella moottorilla joista kaksi on kiinni renkaissa pystytän toteuttamaan.
 
 Myös seuraaminen voi tuottaa ongelmia koska infrapunasensori tarvitsee beaconin tunnistaakseen mihin suuntaan objekti liikkui, joten seurattavaan esteeseen tulee ehkä lisäksi kiinnittää laitteen kauko-ohjain, jonka signaalia robotti seuraa. Toinen vaihtoehto on että robotti pyrkii kääntymällä molempiin suuntiin kohdentamaan kohteen uudelleen mikäli se katoaa "näkökentästä".
 
 Robotin perusominaisuudet vaikuttaa melko helpoilta toteuttaa, joten suuri osa ajasta varmaankin menee näiden ja mahdollisten muiden vastaan tulevien ongelmien ratkomiseen tai minimointiin.
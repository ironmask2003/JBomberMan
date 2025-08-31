# JBomberMan 🎮💣

Un clone del classico gioco Bomberman sviluppato in Java utilizzando Swing per la grafica e il sound system.

## 📋 Descrizione del Progetto

JBomberMan è un'implementazione del famoso gioco arcade Bomberman, dove il giocatore controlla un personaggio che deve attraversare vari livelli piazzando bombe per eliminare nemici e ostacoli, raccogliere power-up e raggiungere l'uscita di ogni stage.

### ✨ Caratteristiche Principali

- **Gameplay Classico**: Movimento del giocatore, piazzamento bombe, esplosioni ed eliminazione nemici
- **Sistema di Livelli**: Multipli stage con difficoltà crescente
- **Nemici AI**: Diversi tipi di nemici con comportamenti unici (Cuppen, Denkyun, Pakupa, Puropen)
- **Power-ups**: Potenziamenti per aumentare velocità, potenza bombe e numero di bombe
- **Sistema Audio**: Effetti sonori e musiche di sottofondo
- **Sistema di Salvataggio**: Possibilità di continuare il gioco con password
- **Menu Interattivi**: Menu principale, mappa dei livelli e schermata continue

## 🎯 Modalità di Gioco

1. **Normal Mode**: Gioca attraverso tutti i livelli in sequenza
2. **Continue Mode**: Continua da un livello specifico utilizzando una password
3. **Password Mode**: Inserisci una password per accedere a livelli specifici

## 🗂️ Struttura del Progetto

### 📁 Directory Principali

```
JBomberMan/
├── src/                          # Codice sorgente
│   ├── main/                     # Classe principale del gioco
│   ├── entita/                   # Entità del gioco (Player, nemici, ostacoli)
│   ├── stages/                   # Gestione gameplay e livelli
│   ├── menu/                     # Sistema di menu
│   ├── powerUp/                  # Sistema power-ups
│   ├── continuePasswordPanel/    # Pannelli per continue e password
│   ├── audio/                    # Gestione audio
│   ├── tile/                     # Sistema di tilemap
│   ├── observer/                 # Pattern Observer per eventi
│   ├── data/                     # Salvataggio e caricamento dati
│   ├── pointBar/                 # Barra punteggio
│   ├── stageStart/               # Schermata inizio livello
│   └── title/                    # Schermata titolo
├── res/                          # Risorse del gioco
│   ├── audio/                    # File audio (.wav)
│   ├── maps/                     # File mappe (.txt) e immagini background
│   ├── tiles/                    # Sprite delle tiles
│   ├── player1/                  # Sprite del giocatore
│   ├── [nemici]/                 # Sprite dei vari nemici
│   ├── powerUp/                  # Sprite dei power-up
│   ├── background/               # Sfondi del gioco
│   ├── menu/                     # Elementi grafici menu
│   └── continuePage/             # Elementi schermata continue
└── bin/                          # File compilati (.class)
```

### 🎮 Componenti Principali

#### 🕹️ Entità del Gioco (`entita/`)
- **Player.java**: Gestisce il personaggio del giocatore, movimento, animazioni e interazioni
- **Enemy.java**: Classe base per tutti i nemici
- **EnemyCuppen/Denkyun/Pakupa/Puropen**: Diversi tipi di nemici con IA specifica
- **PlayerBomb.java**: Gestisce le bombe piazzate dal giocatore
- **BombExplosion.java**: Effetti esplosione delle bombe
- **Obstacle.java**: Ostacoli distruggibili e non
- **Portal.java**: Portale di uscita del livello

#### 🎯 Sistema di Gioco (`stages/`)
- **Gameplay.java**: Controllo principale del gameplay e finestra di gioco
- **GamePanel.java**: Pannello principale dove avviene il rendering del gioco
- **KeyHandler.java**: Gestione input da tastiera
- **CollisionChecker.java**: Sistema di rilevamento collisioni

#### 🎵 Sistema Audio (`audio/`)
- **AudioManager.java**: Gestione di musiche ed effetti sonori
- Include tracce per esplosioni, movimento, menu, vittoria e sconfitta

#### 🗺️ Sistema Mappe
Le mappe sono definite in file di testo (`.txt`) che utilizzano numeri per rappresentare diversi tipi di tile:
- `0`: Spazio vuoto
- `1`: Ostacolo distruggibile  
- `2`: Ostacolo fisso
- `18`: Portale di uscita
- Altri numeri: Bordi e decorazioni

## 🚀 Come Eseguire il Progetto

### Prerequisiti
- **Java 11 o superiore**
- **IDE Java** (Eclipse, IntelliJ IDEA, Visual Studio Code)

### Compilazione ed Esecuzione

1. **Clona il repository:**
   ```bash
   git clone [URL_REPOSITORY]
   cd JBomberMan
   ```

2. **Compila il progetto:**
   ```bash
   javac -d bin --module-path src src/main/JBomberMan.java
   ```

3. **Esegui il gioco:**
   ```bash
   java -cp bin main.JBomberMan
   ```

### 🎮 Controlli di Gioco

- **Frecce Direzionali**: Movimento del personaggio
- **E**: Piazza bomba
- **Enter**: Conferma/Continua
- **ESC**: Exit

## 🏗️ Architettura del Codice

Il progetto utilizza diversi pattern di design:

- **Observer Pattern**: Per la comunicazione tra entità del gioco
- **State Pattern**: Per la gestione degli stati del gioco
- **Factory Pattern**: Per la creazione di nemici e power-up
- **MVC Pattern**: Separazione tra logica di gioco, visualizzazione e controlli

## 🎨 Risorse Grafiche e Audio

Il gioco include:
- **Sprite animati** per personaggi e nemici
- **Tileset** per la costruzione delle mappe
- **Effetti sonori** per azioni di gioco
- **Musiche di sottofondo** per diversi momenti di gioco
- **Interfacce grafiche** per menu e schermate

## 📈 Funzionalità Avanzate

- **Sistema di punteggio** dinamico
- **Salvataggio progresso** tramite password
- **Animazioni fluide** dei personaggi
- **Effetti particellari** per esplosioni
- **Sistema di vite** e continue
- **Progressione difficoltà** tra i livelli

## 🐛 Debug e Testing

Il progetto include sistema di logging e gestione errori per:
- Caricamento risorse
- Gestione collisioni
- Stati di gioco
- Input utente

---

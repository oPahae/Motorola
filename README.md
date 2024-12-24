# Simulateur Assembleur Motorola 6809

## Description
Ce projet est un **simulateur assembleur Motorola 6809** qui prend en charge divers modes d'adressage et instructions du microprocesseur Motorola 6809. Il permet aux développeurs de simuler des programmes assembleurs en fournissant des fonctionnalités telles que la gestion des fichiers, l'utilisation des étiquettes, l'ajout de commentaires, et l'exécution des instructions.

Ce simulateur vise à être un outil robuste pour l'apprentissage, les tests et le débogage des programmes assembleurs pour le microprocesseur Motorola 6809.

## Fonctionnalités
- **Modes d'adressage pris en charge :**
  - Inhérent
  - Immédiat
  - Direct
  - Étendu
  - Indexé

- **Support des étiquettes et des commentaires :**
  - Utilisez des étiquettes pour définir des emplacements dans le programme.
  - Ajoutez des commentaires pour une meilleure lisibilité.

- **Gestion des fichiers :**
  - Ouvrez, enregistrez et gérez des fichiers de programmes assembleurs dans le simulateur.

- **Instructions supportées :**
  - **Chargement et stockage :**
    - `LDA`, `LDB`, `LDU`, `LDS`, `LDX`, `LDY`
    - `STA`, `STB`

  - **Incrémentation et décrémentation :**
    - `INCA`, `INCB`, `DECA`, `DECB`

  - **Opérations arithmétiques :**
    - `ADDA`, `ADDB`, `SUBA`, `SUBB`

  - **Échange et transfert :**
    - `EXG`, `TFR`

  - **Pas d'opération et interruption :**
    - `NOP`, `SWI`, `END`

  - **Effacement et décalage :**
    - `CLRA`
    - Décalages logiques (`LSLA`, `LSLB`, `LSRA`, `LSRB`)
    - Rotations (`ROLA`, `ROLB`, `RORA`, `RORB`)

  - **Opérations de comparaison :**
    - `CMPA`, `CMPB`, `CMPU`, `CMPX`, `CMPY`

  - **Complément et négation :**
    - `COMA`, `COMB`, `NEGA`, `NEGB`

- **Instructions de branchement :**
  - Inconditionnelles : `JMP`, `RTS`, `BRA`
  - Conditionnelles :
    - `BEQ`, `BNE`
    - `BMI`, `BPL`
    - `BCC`, `BCS`
    - `BVC`, `BVS`

## Installation
1. Clonez le dépôt :
    ```bash
    git clone https://github.com/votre-utilisateur/simulateur-motorola-6809.git
    ```
2. Naviguez dans le répertoire du projet :
    ```bash
    cd simulateur-motorola-6809
    ```
3. Compilez et exécutez le simulateur (les instructions varient selon le langage d'implémentation).

## Utilisation
1. Chargez votre fichier assembleur dans le simulateur.
2. Exécutez le programme pas à pas ou entièrement.
3. Visualisez les registres, la mémoire et les drapeaux pour déboguer et analyser votre programme.

## Exemple de programme
```assembly
; Exemple : Addition simple de deux nombres
	LDA #$10        ; Charge 10 dans l'accumulateur A
        ADDA #$20       ; Ajoute 20 à l'accumulateur A
        STA $1000      	; Stocke le résultat à l'adresse mémoire $1000
        END
```
## Programme plus complexe
```assembly
 	NOP		;rien
	LDA #$01 	;A = 01
	LDX #0001	;X = 0000
	STA $0002	;A -> $0002
	
	LDB $02		;B <- DP+$02
	CLRB		;B = 0
	DECA       	;A--
	DECA	   	;A--
	
	LDB 1,X		;B <- $X+1
	EXG A,B		;A <-> B
	TFR A,DP	;A -> DP
	ADDA #$FE	;A += FE
	LDB #$5A	;B = 5A
	COMB		;comp1(B)
	
	NEGA		;comp2(A)
	LSLB
	LSRB
	ADDA #$FF	;A += FF
	ROLB
	RORB
	END 
```

## Contribution
Les contributions sont les bienvenues ! Veuillez suivre ces étapes :
1. Forkez le dépôt.
2. Créez une nouvelle branche pour votre fonctionnalité ou correction de bug.
3. Soumettez une pull request avec une description détaillée de vos modifications.

## Licence
Ce projet est sous le cadre du module 'ARCHITECHTURE DES ORDINATEURS' en FST Settat Liscence génie informatique S5.

## Contact
Pour des questions ou des retours, veuillez contacter lam.bahae7@gmail.com

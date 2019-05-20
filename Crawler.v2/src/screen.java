import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class screen extends JFrame {
    Enemy enemy;
    Inventory inv;
    Formatter saveStats;
    Formatter saveInv;
    Scanner loadingStats;
    Scanner loadingInv;
    Item drop = new Item();
    Player hero = new Player();
    // Create NPC Vendors
    Gerarius gerarius = new Gerarius();
    Alb alb = new Alb();
    Wiz wiz = new Wiz();
    Gooblern gooblern = new Gooblern();
    private JTextArea log = new JTextArea();
    private JTextField action = new JTextField();
    // ImageIcon img = new
    // ImageIcon("C:\\Users\\Jason\\Desktop\\Goods\\GodBlessThisImage.png");
    public screen() {
        super("Tales of Evanus");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        // setIconImage(img.getImage());
        setVisible(true);
        add(action, BorderLayout.SOUTH);
        action.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Uses Players current 'state' to determine available actions
                switch (hero.state) {
                // Ground State
                case "free":
                    Actions(event.getActionCommand());
                    action.setText("");
                    break;
                // In combat
                case "combat":
                    Combat(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at blacksmith
                case "shoppingBLA":
                    ShopBLA(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at alchemist
                case "shoppingALC":
                    ShopALC(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at library
                case "shoppingLIB":
                    ShopLIB(event.getActionCommand());
                    action.setText("");
                    break;
                case "selling":
                    Sell(event.getActionCommand());
                    action.setText("");
                    break;
                // Going through inventory
                case "rummaging":
                    Rummage(event.getActionCommand());
                    action.setText("");
                    break;
                // Adding point to ability after level up
                case "adding":
                    Adding(event.getActionCommand());
                    action.setText("");
                    break;
                    
                case "gambling":
                    Gamble(event.getActionCommand());
                    action.setText("");
                    break;
                    
                case "donating":
                    Donate(event.getActionCommand());
                    action.setText("");
                    break;
                case "end":
                    End(event.getActionCommand());
                    action.setText("");
                    break;
                }
            }
        });
        add(new JScrollPane(log));
        log.setEditable(false);
        log.setBackground(Color.BLACK);
        log.setForeground(Color.WHITE);
    }
    public void start() {
        // Introduction to story
        log.append("You are an ordinary adventurer, hungry for recognition in the land of Evanus.");
        log.append(
                "\nTo fulfill your quest, you devise a plan to slay a dragon that resides in the mountains and has been terrorizing the village of Flintonia.\n");
        log.append(
                "\nWhile your quest is of utmost importance, perhaps preparing yourself for such a moumentous feat would be wise...\n");
        log.append("\nYou begin your journey in the village of Flintonia, where will you go?\n");
        log.append("(Type 'actions' for a list of commands)");
        // Initialize NPC vendors' inventories
        gerarius.inventory();
        alb.inventory();
        wiz.inventory();
        // Initialize Player starting weapon
        hero.inventory.add(new Dagger("Dinky Dagger", 4, 2, 0));
        hero.setDMG(hero.inventory.get(0).getDMG());
        hero.setMinDMG(hero.inventory.get(0).getMinDMG());
        hero.inventory.get(0).setIsEquipped(true);
    }
    private void Actions(String act) {
        log.append("\n\n" + act.toUpperCase() + "\n");
        switch (act) {
        
        case "load":
            openLoader();
            readLoader();
            closeLoader();
        break;    
        
        case "save":
            createSave();
            dataSave();
            closeSave();
        break;
        
        case "egg":
            log.append(
                    "oooooooo_oo_________________ooo____oo__________oo____oo______________________ooo________________________oo________\r\n" +
                    "___oo____oo_ooo___ooooo_____oooo___oo__ooooo___oo____oo_ooo___ooooo__oo_ooo___oo____ooooo__oo_ooo___oooooo__oooo__\r\n" +
                    "___oo____ooo___o_oo____o____oo_oo__oo_oo____o_oooo___ooo___o_oo____o_ooo___o__oo___oo___oo_ooo___o_oo___oo_oo___o_\r\n" +
                    "___oo____oo____o_ooooooo____oo__oo_oo_ooooooo__oo____oo____o_ooooooo_oo_______oo___oo___oo_oo____o_oo___oo___oo___\r\n" +
                    "___oo____oo____o_oo_________oo___oooo_oo_______oo__o_oo____o_oo______oo_______oo___oo___oo_oo____o_oo___oo_o___oo_\r\n" +
                    "___oo____oo____o__ooooo_____oo____ooo__ooooo____ooo__oo____o__ooooo__oo______ooooo__oooo_o_oo____o__oooooo__oooo__");
        break;    
        case "actions":
            log.append("\nactions: list of actions (varies on location)"
                    + "\nmap: lists places to go"
                    + "\nlook: observe the area around you"
                    + "\ncombat: lists actions for combat"
                    + "\ninventory: displays a list your items"
                    + "\nstats: display a list of your stats"
                    + "\nshop: opens shops at npc vendors"
                    + "\nfight: look for things to fight"
                    + "\ngamble: roll some dice for some money at the tavern"
                    + "\ndonate: donate to the temple for healing");
            break;
            
        case "look":
            Random r = new Random();
            switch(hero.location) {
            case "town":
                String[] desc = {
                        "\nThere are some children playing with a ball in the streets.",
                        "\nA herald is giving a lecture.",   
                        "\nA woman carries a sack of groceries."
                };
                log.append(desc[r.nextInt(desc.length)]);
            break;
                
            case "mountain":
                
            break;
            
            case "forest":
                
            break;
            
            case "dungeon":
                
            break;
            
            case "tavern":
                String[] descT = {
                        "\nA shady looking goblin sits in the corner fidgeting with a coin. He beckons to you.",
                        "\nThe bartender cleans glasses from the shelves.",   
                        "\nA group of old men sit at a table, slamming the table and laughing heavily.",
                        "\nTwo men sit at the counter whispering to each other."
                };
                log.append(descT[r.nextInt(descT.length)]);
            break;
                
            case "temple":
                
            break;
            
            case "blacksmith":
                
            break;
            
            case "alchemist":
                
            break;
            
            case "library":
                
            break;
            
            case "market":
                
            break;
            
            }
            break;
        case "combat":
            log.append("" + "\nattack: basic attack" + "\nmagic: magic attack"
                    + "\nuse (number of index of item in inventory): use an item" + "\nrun: run away from the battle");
            break;
        case "stats":
            log.append("\nHealth: " + hero.getHP() + " / " + hero.getMaxHP() + "\nLevel: " + hero.getLevel() + "\nXP: "
                    + hero.getEXP() + " / " + hero.getMaxEXP() + "\nDamage: " + hero.getMinDMG() + " - " + hero.getDMG()
                    + "\nMagic Damage: " + hero.getMagMinDMG() + " - " + hero.getMagDMG()
                    + "\nStrength (Increases max damage and health): " + hero.getSTR()
                    + "\nAgility (Increases chance to run from combat): " + hero.getAGL()
                    + "\nIntelligence (Increases magic power and potion potency): " + hero.getINT());
            break;
        case "map":
            log.append("\nTOWN \nMARKET \nBLACKSMITH \nLIBRARY \nTEMPLE \nTAVERN \nALCHEMIST \nFOREST \nDUNGEON \nDESERT \nPAGODA \nMOUNTAIN");
            break;
        case "inventory":
            log.append("\nYou open your backpack.");
            openInv();
            hero.state = "rummaging";
            break;
        // LOCATIONS
        // COMBAT AREAS
        case "forest":
            log.append("\nYou stand in the clearing of a forest.");
            hero.location = "forest";
            break;
        case "dungeon":
            log.append("\nYou pry open the creaky, rusted gates to the old dungeon near Flintonia.");
            hero.location = "dungeon";
            break;
        case "desert":
            log.append("\nYou stand on the soft sands of the Flintonia desert. There is an incredible amount of cactus.");
            hero.location = "desert";
            break;
        case "pagoda":
            log.append("\nYou step into the empty hall of the forgotten pagoda. It smells of sweat.. and doritos.");
            hero.location = "pagoda";
            break;
        case "mountain":
            log.append("\nYou stand at the foot of Mount Saint Morain. It tremors very slightly.");
            hero.location = "mountain";
            break;
        // TOWN AREAS
        case "town":
            log.append("\nYou stand at the village center of Flintonia.");
            hero.location = "town";
        break;
        
        case "tavern":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nWelcome to the Flintonia Tavern!");
                hero.location = "tavern";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
        break;
        
        case "temple":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nYou step into the hall of the Flintonia Temple.");
                hero.location = "temple";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
        break;
        
        case "donate":
            if (hero.location.equalsIgnoreCase("temple")) {
                log.append("\nHow much would you like to donate? (1HP / 2g)");
                hero.state = "donating";
            } else {
                log.append("\nCould not perform action.");
            }
        break;
        case "market":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nWelcome to the Flintonia Village Market");
                openInv();
                hero.location = "market";
                hero.state = "selling";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
            break;
        case "blacksmith":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nWelcome to Gerarius's Blacksmithery!");
                hero.location = "blacksmith";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
            break;
        case "alchemist":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nWelcome to Alb's Alchemy!");
                hero.location = "alchemist";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
            break;
        case "library":
            if (hero.location.equalsIgnoreCase("town")) {
                log.append("\nWelcome to the Flintonia Public Library!");
                hero.location = "library";
            } else {
                log.append("\nYou must return to Flintonia before traveling here.");
            }
            break;
            
        case "gamble":
            if (hero.location.equalsIgnoreCase("tavern")) {
                if(gooblern.alive) {
                    log.append("\nHehe...You feeling lucky, bub?");
                    log.append("\nI got a coin and I want some money. Heads or tails?");
                    log.append("\n(GUESS) (AMOUNT)");
                    hero.state = "gambling";
                } else {
                    log.append("\nGooblern's corpse lies bent over on a chair...");
                }
            } else {
                log.append("\nYou can't gamble here.");
            }
        break;
        case "fight":
            switch (hero.location) {
            case "blacksmith":
                if (gerarius.alive) {
                    log.append("\nYou challenge Gerarius to a duel!");
                    enemy = new Gerarius();
                    hero.state = "combat";
                } else {
                    log.append("\nYou kick Gerarius's corpse...");
                }
                break;
            case "alchemist":
                if (alb.alive) {
                    log.append("\nYou challenge Alb to a duel!");
                    enemy = new Alb();
                    hero.state = "combat";
                } else {
                    log.append("\nYou kick Alb's corpse...");
                }
                break;
            case "library":
                if (wiz.alive) {
                    log.append("\nYou challenge Wiz to a duel!");
                    enemy = new Wiz();
                    hero.state = "combat";
                } else {
                    log.append("\nYou kick Wiz's corpse...");
                }
                break;
                
            case "tavern":
                enemy = new Drunkard();
                log.append("\nYou start a fight with a " + enemy.getName() + "!");
                hero.state = "combat";
            break;
            
            case "temple":
                log.append("\nNo fighting in this Christian Temple.");
            break;
            case "mountain":
                
                String[] searchM = {
                        "\nYou bravely ascend the mountains to hunt the Dragoon."
                };
                
                Enemy[] enemiesM = {
                        new FinalBoss()
                };
                
                String[] descM = {
                        "\nYou've awoken the "
                };
                
                SetupCombat(searchM, enemiesM, descM);
                
                break;
            case "forest":
                
                String[] searchF = {
                        "\nYou wander the forest"
                };
                
                Enemy[] enemiesF = {
                        new Bear(),
                        new Mushroom(),
                        new ChubbyLizard()
                };
                
                String[] descF = {
                      "\nYou step on a ",
                      "\nYou wake up a "
                };
                
                SetupCombat(searchF, enemiesF, descF);
                break;
            case "dungeon":
                
                String[] searchD = {
                        "\nYou wander the empty halls of the dungeon..."
                };
                
                Enemy[] enemiesD = {
                        new Troll(),
                        new Slime(),
                        new PossessedArmor(),
                        new Mimic(),
                };
                
                String[] descD = {
                      "\nYou step on a ",
                      "\nYou wake up a "
                };
                
                SetupCombat(searchD, enemiesD, descD);
                
                break;
            default:
                log.append("There was nothing to fight.");
            }
            if(hero.state.equalsIgnoreCase("combat")) {
                log.append("\n(Entered COMBAT)");
                openInv();
            }
            break;
        case "shop":
            switch (hero.location) {
            case "blacksmith":
                if (gerarius.alive) {
                    log.append("\n" + gerarius.getWelcome());
                    hero.state = "shoppingBLA";
                    openNPCInv(gerarius.inventory);
                } else {
                    log.append("\nGerarius is dead...");
                }
                break;
            case "alchemist":
                if (alb.alive) {
                    log.append("\n" + alb.getWelcome());
                    hero.state = "shoppingALC";
                    openNPCInv(alb.inventory);
                } else {
                    log.append("\nAlb is dead...");
                }
                break;
            case "library":
                if (wiz.alive) {
                    log.append("\n" + wiz.getWelcome());
                    hero.state = "shoppingLIB";
                    openNPCInv(wiz.inventory);
                } else {
                    log.append("\nWiz is dead...");
                }
                break;
                
                default: log.append("\nYou are not at a shop.");
            }
            break;
        default:
            log.append("Could not perform action.");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************COMBAT*********************************************************\\
    private void Combat(String act) {
        String subact = act.substring(0, 3);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        boolean EndTurn = false;
        log.append("\n\n" + act.toUpperCase() + "\n");
        switch (subact) {
        case "act":
            log.append("\nattack: basic attack" + "\nmagic: cast a magic attack (if available)"
                    + "\ninventory: opens inventory" + "\nuse (number of index of item): use an item"
                    + "\nrun: chance to flee from combat");
            break;
        case "att":
            int damage = hero.attack(hero.getDMG(), hero.getMinDMG());
            log.append("\nYou attack " + enemy.getName() + " for " + damage + " damage.");
            enemy.setHP(damage);
            EndTurn = true;
            break;
        case "mag":
            if (hero.getCurrentSpell().getIsEquipped()) {
                if (hero.getCurrentSpell().getCooldown() == hero.getCurrentSpell().getReady()) {
                    int magDamage = hero.attack(hero.getMagDMG(), hero.getMagMinDMG());
                    log.append("\nYou casted your spell at " + enemy.getName() + " for " + magDamage + " damage.");
                    hero.casted();
                    enemy.setHP(magDamage);
                    EndTurn = true;
                } else {
                    log.append("\nYour spell is on cooldown.");
                }
            } else {
                log.append("\nYou do not have a spell to cast.");
            }
            break;
        case "use":
            int slot = Integer.parseInt(slotSTR);
            if (hero.inventory.get(slot).getType().equalsIgnoreCase("useable")) {
                Item use = hero.inventory.get(slot);
                switch (use.getName()) {
                
                    case "Healing Potion":
                        hero.setHP(use.getHealing());
                        hero.inventory.remove(slot);
                        EndTurn = true;
                    break;
                    
                    case "Greater Healing Potion":
                        hero.setHP(use.getHealing());
                        hero.inventory.remove(slot);
                        EndTurn = true;
                    break;
                    
                    case "Beer":
                        hero.setHP(use.getHealing());
                        hero.inventory.remove(slot);
                        EndTurn = true;
                    break;    
                }
                
            } else {
                log.append("\nCannot use that item.");
            }
            
            inv.list.setText("");
            inv.list.append("Gold " + hero.gold + "\n");
            inv.showInv(hero.inventory);
            
            break;
        case "run":
            Random r = new Random();
            int chance = r.nextInt(10);
            if (chance > 5) {
                enemy = null;
                log.append("\nYou successfully run back to town.");
                inv.dispose();
                hero.location = "town";
                hero.state = "free";
            } else {
                log.append("\nYou try to escape, but are caught.");
                EndTurn = true;
            }
            break;
            
        case "stats":
            
        break;
        default:
            log.append("\nYou cannot perform that action while in combat.");
        }
        // END OF PLAYER TURN
        if (EndTurn) {
            if (enemy.getHP() > 0) {
                log.append("\n" + enemy.getName() + " has " + enemy.getHP() + " health.");
                log.append("\n" + enemy.getName() + " slashes at you for " + hero.PlayerTakesDamage(enemy.getAtt())
                        + " damage.");
                if (hero.getHP() <= 0) {
                    log.append("\nYou died.");
                    action.setEditable(false);
                } else {
                    log.append("\nYou have " + hero.getHP() + " health.");
                }
            } else {
                // END OF COMBAT
                log.append("\nYou have slain " + enemy.getName() + "!");
                drop = enemy.getItemDrops();
                hero.inventory.add(drop);
                log.append("\nYou picked up " + drop.getName() + ".");
                ////////
                hero.getCurrentSpell().setCooldown(hero.getCurrentSpell().getReady() - hero.getCurrentSpell().getCooldown());
                enemy.setAlive(false);
                inv.dispose();
                
                boolean leveled = hero.leveling(enemy.getEXP());
                // Check if player leveled up
                if (enemy.getName().equalsIgnoreCase("the dragoon")) {
                    ENDGAME();
                } else if (leveled) {
                    log.append("\n LEVEL UP!\n");
                    log.append("\n Choose a trait to add a point to (STR, AGL, or INT).\n");
                    hero.state = "adding";
                } else {
                    log.append("\nYou gained " + enemy.getEXP() + "XP.");
                    hero.state = "free";
                }
                
                log.append("\n(Out of COMBAT)");
                enemy = null;
            }
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************BLACKSMITH*********************************************************\\
    private void ShopBLA(String act) {
        
        String subact = act.substring(0, 3);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n"+act.toUpperCase()+"\n");
        switch (subact) {
        case "act":
            log.append("\nbuy (number of index of item): buy an item");
            break;
        case "buy":
            int slot = Integer.parseInt(slotSTR);
            int value = gerarius.inventory.get(slot).getValue();
            if (hero.gold >= value) {
                hero.gold -= value;
                hero.inventory.add(gerarius.inventory.get(slot));
                log.append("\nBought " + gerarius.inventory.get(slot).getName());
                gerarius.inventory.remove(slot);
                log.append("\nDon't have too much fun with that one!");
                inv.list.setText("");
                inv.list.append("Gold " + hero.gold + "\n");
                inv.NPCshowInv(gerarius.inventory);
            } else {
                log.append("\nSorry lad', you'll need more gold for that one!");
            }
        break;
            
        case "lea":
            log.append("\nYou leave the " + hero.location + ".");
            hero.location = "town";
            hero.state = "free";
            inv.dispose();
        break;
        default:
            log.append("\nHuh? What did you want?");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************ALCHEMIST*********************************************************\\
    private void ShopALC(String act) {
        
        String subact = act.substring(0, 3);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n"+act.toUpperCase()+"\n");
        switch (subact) {
        case "act":
            log.append("\nbuy (number of index of item): buy an item");
            break;
        case "buy":
            int slot = Integer.parseInt(slotSTR);
            int value = alb.inventory.get(slot).getValue();
            if (hero.gold >= value) {
                hero.gold -= value;
                hero.inventory.add(alb.inventory.get(slot));
                log.append("\nBought " + alb.inventory.get(slot).getName());
                //alb.inventory.remove(slot);
                log.append("\nThat one is a pretty color, am i right? Hehe!");
                inv.list.setText("");
                inv.list.append("Gold " + hero.gold + "\n");
                inv.NPCshowInv(alb.inventory);
            } else {
                log.append("\nOoooh! I can't give that one away for such a low price!");
            }
            break;
            
        case "lea":
            log.append("\nYou leave the " + hero.location + ".");
            hero.location = "town";
            hero.state = "free";
            inv.dispose();
        break;
        default:
            log.append("\nWhat? What was that?");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************LIBRARY*********************************************************\\
    private void ShopLIB(String act) {
        
        String subact = act.substring(0, 3);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n"+act.toUpperCase()+"\n");
        switch (subact) {
        case "actions":
            log.append("\nbuy (number of index of item): buy an item");
            break;
        case "buy":
            int slot = Integer.parseInt(slotSTR);
            int value = wiz.inventory.get(slot).getValue();
            if (hero.gold >= value) {
                hero.gold -= value;
                hero.inventory.add(wiz.inventory.get(slot));
                log.append("\nBought " + wiz.inventory.get(slot).getName());
                wiz.inventory.remove(slot);
                log.append("\nA wise decision...");
                inv.list.setText("");
                inv.list.append("Gold " + hero.gold + "\n");
                inv.NPCshowInv(wiz.inventory);
            } else {
                log.append("\nIt appears you have insufficient gold for that.");
            }
            break;
            
        case "lea":
            log.append("\nYou leave the " + hero.location + ".");
            hero.location = "town";
            hero.state = "free";
            inv.dispose();
        break;
        default:
            log.append("\n...");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************MARKET*********************************************************\\
    private void Sell(String act) {
        String subact = act.substring(0, 4);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n"+act.toUpperCase()+"\n");
        switch (subact) {
        case "acti":
            log.append("\nsell (number of index of item): sell an item");
            break;
        case "sell":
            int slot = Integer.parseInt(slotSTR);
            if (!hero.inventory.get(slot).getIsEquipped()) {
                
                log.append("\nSold " + hero.inventory.get(slot).getName());
                hero.gold += hero.inventory.get(slot).getValue();
                hero.inventory.remove(slot);
                
            } else {
                log.append("\nYou cannot sell a weapon you have equipped");
            }
            inv.list.setText("");
            inv.list.append("Gold " + hero.gold + "\n");
            inv.showInv(hero.inventory);
            break;
            
        case "leav":
            log.append("\nYou leave the " + hero.location + ".");
            hero.location = "town";
            hero.state = "free";
            inv.dispose();
        break;
        default:
            log.append("\nCould not perform action");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    //*********************************************************INVENTORY*********************************************************\\
    private void Rummage(String act) {
        
        String subact = act.substring(0, 5);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n"+act.toUpperCase()+"\n");
        switch (subact) {
        case "action":
            log.append("\nsequip (number of index of item): equip an item");
        break;
            
        case "stats":
            int slot1 = Integer.parseInt(slotSTR);
            
            //can condense...
            if (hero.inventory.get(slot1).getType().equalsIgnoreCase("weapon")) {
                
                log.append("\n" + hero.inventory.get(slot1).getName().toUpperCase()
                        + "\nDamage: " + hero.inventory.get(slot1).getMinDMG() + " - " + hero.inventory.get(slot1).getDMG()
                        );
                
            } else if(hero.inventory.get(slot1).getType().equalsIgnoreCase("magic")) {
                
                log.append("\n" + hero.inventory.get(slot1).getName().toUpperCase()
                        + "\nDamage: " + hero.inventory.get(slot1).getMinDMG() + " - " + hero.inventory.get(slot1).getDMG()
                        );
                
            } else {
                log.append("\nThis item has no stats.");
            }
        break;
        case "equip":
            int slot = Integer.parseInt(slotSTR);
            // EQUIPPING MELEE ITEMS
            if (hero.inventory.get(slot).getType().equalsIgnoreCase("weapon")) {
                log.append("\nEquipped " + hero.inventory.get(slot).getName());
                hero.setDMG(hero.inventory.get(slot).getDMG() + hero.getSTR()/2);
                hero.setMinDMG(hero.inventory.get(slot).getMinDMG());
                // Sort through all melee items to set 'equipped' to false
                for (int i = 0; i < hero.inventory.size(); i++) {
                    if (hero.inventory.get(i).getType().equalsIgnoreCase("weapon")) {
                        hero.inventory.get(i).setIsEquipped(false);
                    }
                }
                hero.inventory.get(slot).setIsEquipped(true);
                inv.list.setText("");
                inv.list.append("Gold " + hero.gold + "\n");
                inv.showInv(hero.inventory);
                // EQUIPPING MAGIC ITEMS
            } else if (hero.inventory.get(slot).getType().equalsIgnoreCase("magic")) {
                log.append("\nEquipped " + hero.inventory.get(slot).getName());
                hero.setMagDMG(hero.inventory.get(slot).getDMG() + hero.getINT()/3);
                hero.setMinMagDMG(hero.inventory.get(slot).getMinDMG());
                // Sort through all magic items to set 'equipped' to false
                for (int i = 0; i < hero.inventory.size(); i++) {
                    if (hero.inventory.get(i).getType().equalsIgnoreCase("magic")) {
                        hero.inventory.get(i).setIsEquipped(false);
                    }
                }
                hero.inventory.get(slot).setIsEquipped(true);
                inv.list.setText("");
                inv.list.append("Gold " + hero.gold + "\n");
                inv.showInv(hero.inventory);
            } else {
                log.append("\nCannot equip item.");
            }
            break;
            
        case "close":
            log.append("\nYou close your backpack.");
            hero.state = "free";
            inv.dispose();
        break;
        default:
            log.append("\nCould not perform action (You must CLOSE your inventory before continuing your adventure.)");
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    
    private void Adding(String act) {
        switch (act) {
        case "str":
            hero.setSTR(1);
            log.append("\nYou feel stronger.");
            hero.setMaxHP(hero.getSTR()/4);
            hero.state = "free";
            break;
        case "agl":
            hero.setAGL(1);
            log.append("\nYou feel faster.");
            hero.state = "free";
            break;
        case "int":
            hero.setINT(1);
            log.append("\nYou feel smarter.");
            hero.state = "free";
            break;
        default:
            log.append("\nYou must allocate a point into a trait before continuing.");
        }
    }
    
    private void Gamble(String act) {
        String guess = "";
        String outcome = "";
        
        String subact = act.substring(0, 5);
        int space = act.indexOf(" ");
        String slotSTR = act.substring(space + 1);
        
        log.append("\n\n" + act.toUpperCase() + "\n");
        
        if(subact.equalsIgnoreCase("leave")) {
            
            log.append("\nI know I'll be seeing you around again bub!");
            hero.state = "free";
            
        } else if(subact.equalsIgnoreCase("fight")) {
            
            log.append("\nIf yous looking for a fight, I ain't gonna turn one down!");
            enemy = new Gooblern();
            hero.state = "combat";
            
        } else {
            int bet = Integer.parseInt(slotSTR);
        if(bet > hero.gold) {
            log.append("\nSlow down bub! You can't bet more than you have on you!");
        } else {
            switch(subact) {
                case "heads":
                    guess = "heads";
                    log.append("\nHeads it is!");
                break;
                
                case "tails":
                    guess = "tails";
                    log.append("\nI'm more of a heads guy, but whateve' floats yer boat!!");
                break;
                }
            
                Random r = new Random();
                int NUMoutcome = r.nextInt(9);
                if(NUMoutcome >= 3) {
                    outcome = "heads";
                    log.append("\nThe coin lands on heads.");
                } else {
                    outcome = "tails";
                    log.append("\nThe coin lands on tails.");
                }
                
                if(guess.equalsIgnoreCase(outcome)) {
                    log.append("\nWhat a lucky fu- I mean... Take yer damn gold!");
                    hero.gold += bet;
                } else {
                    log.append("\nOof! Sorry bub, better luck next time!");
                    hero.gold -= bet;
                }
            }
        }
    }
    
    private void Donate(String act) {
        
        int donation = Integer.parseInt(act);
        log.append("\nYou donate " + donation + " gold.");
        hero.setHP(donation / 2);
        hero.gold -= donation;
        hero.state = "free";
        log.append("\nThank you kind soul.");
        
    }
    
    private void SetupCombat(String[] text, Enemy[] mob, String[] desc) {
        
        Random textR = new Random();
        Random mobR = new Random();
        Random descR = new Random();
        
        log.append(text[textR.nextInt(text.length)]);
        enemy = mob[mobR.nextInt(mob.length)];
        log.append(desc[descR.nextInt(desc.length)] + enemy.getName() + "!");
        hero.state = "combat";
    }
    private void End(String act) {
        log.append("\nThank You for playing!");
    }
    private void ENDGAME() {
        log.append("\nYou have slain the Dragoon");
        log.append("\nThe land is saved from its terror.");
        log.append("\nOr do it be like it is?");
        log.append("\n\nThank You for playing!");
        hero.state = "end";
    }
    private void openInv() {
        inv = new Inventory();
        JButton close = new JButton("Done");
        inv.add(close, BorderLayout.SOUTH);
        close.addActionListener(new closeMenu());
        inv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inv.list.setText("");
        inv.list.append("Gold " + hero.gold + "\n");
        inv.showInv(hero.inventory);
    }
    private void openNPCInv(ArrayList<Item> inve) {
        inv = new Inventory();
        JButton close = new JButton("Done");
        inv.add(close, BorderLayout.SOUTH);
        close.addActionListener(new closeNPCMenu());
        inv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inv.list.append("Gold " + hero.gold + "\n");
        inv.NPCshowInv(inve);
    }
    
    private class closeMenu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            log.append("\nYou close your backpack.");
            hero.state = "free";
            inv.dispose();
        }
        
    }
    
    private class closeNPCMenu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            log.append("\nYou leave the " + hero.location + ".");
            hero.location = "town";
            hero.state = "free";
            inv.dispose();
        }
        
    }
    
    private void disposeInv() {
        for (int i = 0; i < hero.inventory.size(); i++) {
            hero.inventory.remove(i);
        }
    }
    
    private void createSave() {
        try {
            saveStats = new Formatter("stats.txt");
            saveInv = new Formatter("inventory.txt");
            log.append("\nSaved!");
        } catch(Exception e) {
            System.out.println("could not create save file");
        }
    }
    
    private void dataSave() {
        saveStats.format("%d %d %d %d %d %d %d %d %d %d %d %d %d ",
                hero.getHP(),
                hero.getMaxHP(),
                hero.gold,
                hero.getEXP(),
                hero.getMaxEXP(),
                hero.getLevel(),
                hero.getSTR(),
                hero.getAGL(),
                hero.getINT(),
                hero.getDMG(),
                hero.getMinDMG(),
                hero.getMagDMG(),
                hero.getMagMinDMG()
        );
        
        Iterator<Item> playerInv = hero.inventory.iterator();
        while(playerInv.hasNext()) {
            Item item = playerInv.next();
            saveInv.format("%d ", item.getID());
            /*
            int isEquipped;
            if(item.getIsEquipped()) {
                isEquipped = 1;
            } else {
                isEquipped = 0;
            }
            
            switch(item.getType()) {
            
                case "weapon":
                    //id, name, max dmg, min dmg, cost, isEquipped
                    save.format("%d %s %d %d %d %d ",
                        item.getID(),
                        item.getName(),
                        item.getDMG(),
                        item.getMinDMG(),
                        item.getValue(),
                        isEquipped
                    );
                break;
                
                case "magic":
                    //id, name, max dmg, min dmg, cost, isEquipped
                    save.format("%d %s %d %d %d %d ",
                            item.getID(),
                            item.getName(),
                            item.getDMG(),
                            item.getMinDMG(),
                            item.getValue(),
                            isEquipped
                        );
                break;
                    
                case "common":
                    save.format("%d ", item.getID());
                break;
                
                case "useable":
                    save.format("%d ", item.getID());
                break;
                
                default: save.format("ERROR");
            }
            */
        }
    }
    
    private void closeSave() {
        saveStats.close();
    }
    
    private void openLoader() {
        try {
            loadingStats = new Scanner(new File("stats.txt"));
            loadingInv = new Scanner(new File("inventory.txt"));
        } catch(Exception e) {
            log.append("\nCould not find save.");
        }
    }
    
    private void readLoader() {
        
        while(loadingStats.hasNext()) {
            hero.LOADHP(loadingStats.nextInt());
            hero.LOADMaxHP(loadingStats.nextInt());
            hero.gold = loadingStats.nextInt();
            hero.setEXP(loadingStats.nextInt());
            hero.setMaxEXP(loadingStats.nextInt());
            hero.setLevel(loadingStats.nextInt());
            hero.LOADSTR(loadingStats.nextInt());
            hero.LOADAGL(loadingStats.nextInt());
            hero.LOADINT(loadingStats.nextInt());
            hero.LOADDMG(loadingStats.nextInt());
            hero.setMinDMG(loadingStats.nextInt());
            hero.LOADMagDMG(loadingStats.nextInt());
            hero.setMinMagDMG(loadingStats.nextInt());
        }
        
        disposeInv();
        while(loadingInv.hasNext()) {
            ItemIDLibrary(loadingInv.nextInt());
        }
    }
    
    private void closeLoader() {
        loadingStats.close();
    }
    
    private void ItemIDLibrary(int id) {
        switch (id) {
            case 11:
                hero.inventory.add(new Dagger());
            break;
            
            case 12:
                hero.inventory.add(new Shortsword());
            break;
            
            case 13:
                hero.inventory.add(new Quarterstaff());
            break;
            
            case 14:
                hero.inventory.add(new Longsword());
            break;
            
            case 15:
                hero.inventory.add(new Halbard());
            break;
            
            case 16:
                hero.inventory.add(new Spear());
            break;
            
            case 17:
                hero.inventory.add(new Axe());
            break;
            
            case 18:
                hero.inventory.add(new Club());
            break;
            
            case 19:
                hero.inventory.add(new YariSpear());
            break;
            
            case 110:
                hero.inventory.add(new Mace());
            break;
            
            case 111:
                hero.inventory.add(new Glaive());
            break;
            
            case 112:
                hero.inventory.add(new BattleAxe());
            break;
            
            case 113:
                hero.inventory.add(new Katana());
            break;
                
            case 21:
                hero.inventory.add(new Fur());
            break;
            
            case 22:
                hero.inventory.add(new MushroomCap());
            break;
            
            case 23:
                hero.inventory.add(new ChubbyScales());
            break;
            
            case 24:
                hero.inventory.add(new Bones());
            break;
            
            case 25:
                hero.inventory.add(new StickyGoo());
            break;
            
            case 26:
                hero.inventory.add(new StickierGoo());
            break;
            
            case 27:
                hero.inventory.add(new MetalPlate());
            break;
            
            case 28:
                hero.inventory.add(new MetalLock());
            break;
            
            case 29:
                hero.inventory.add(new GooblernsCoin());
            break;
            
            case 210:
                hero.inventory.add(new Fangs());
            break;
            
            case 211:
                hero.inventory.add(new Horns());
            break;
            
            case 31:
                hero.inventory.add(new HealingPotion());
            break;
            
            case 32:
                hero.inventory.add(new GreaterHealingPotion());
            break;
            
            case 41:
                hero.inventory.add(new Fireball());
            break;
        }
    }
}

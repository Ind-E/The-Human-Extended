
package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHuman.actions.FindJunkAction;
import theHuman.actions.FindWeaponAction;


public class OnTheStreets extends theHuman.cards.OnTheStreets {

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FindWeaponAction());
        this.addToBot(new FindJunkAction(magicNumber));
    }
}

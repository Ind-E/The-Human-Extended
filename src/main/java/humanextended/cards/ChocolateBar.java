package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;

import static humanextended.HumanExtendedMod.imagePath;

// renamed from Backpack
public class ChocolateBar extends BaseCard {

    public static final String ID = theHuman.cards.Backpack.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0);

    public ChocolateBar() {
        super(ID, info, imagePath("cards/skill/ChocolateBar.png"));
        setMagic(3);
        setCustomVar("ENERGY", 1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ReducePowerAction(p, p, HealthinessPower.POWER_ID, magicNumber));
        Wiz.atb(new GainEnergyAction(customVar("ENERGY")));
    }
}
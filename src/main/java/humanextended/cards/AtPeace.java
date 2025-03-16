package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.powers.AtPeacePower;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;

// renamed from Silver Generation
public class AtPeace extends BaseCard {

    public static final String ID = theHuman.cards.SilverGeneration.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3);

    public AtPeace() {
        super(ID, info, imagePath("cards/power/AtPeace.png"));
        setMagic(1, 1);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AtPeacePower(magicNumber));
    }
}

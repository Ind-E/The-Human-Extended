package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.tokens.Lollipop;
import humanextended.powers.AppleADayPlusPower;
import humanextended.powers.AppleADayPower;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;

// renamed from Keeping Fit
public class AppleADay extends BaseCard {

    public static final String ID = theHuman.cards.KeepingFit.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.POWER,
            CardRarity.COMMON,
            CardTarget.SELF,
            2);

    public AppleADay() {
        super(ID, info, imagePath("cards/power/AppleADay.png"));
        setMagic(1, 1);
        setCustomVar("LIMIT", 10, 2);
        cardsToPreview = new Lollipop();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            Wiz.applyToSelf(new AppleADayPlusPower(magicNumber, customVar("LIMIT")));
        } else {
            Wiz.applyToSelf(new AppleADayPower(magicNumber, customVar("LIMIT")));
        }
    }
}

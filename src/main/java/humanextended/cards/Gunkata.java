package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.powers.GunkataPower;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import basemod.helpers.TooltipInfo;

import javax.tools.Tool;
import java.util.Collections;
import java.util.List;

import static humanextended.HumanExtendedMod.imagePath;
import static theHuman.HumanMod.getModID;

// renamed from Dual Wielding
public class Gunkata extends BaseCard {

    public static final String ID = theHuman.cards.DualWielding.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(getModID() + ":TechniqueWords");
    private static final TooltipInfo toolTipInfo = new TooltipInfo("[#a83277]" + uiStrings.TEXT[0], uiStrings.TEXT[1]);

    public Gunkata() {
        super(ID, info, imagePath("cards/power/Gunkata.png"));
        setMagic(10, 2);
        setCustomVar("HEALTHINESS_LOSS", 5);
        setCostUpgrade(1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(toolTipInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new GunkataPower());
    }

}


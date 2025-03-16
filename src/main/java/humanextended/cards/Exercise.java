package humanextended.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.patches.subscribers.OnShuffleSubscriber;
import theHuman.powers.HealthinessPower;

import java.util.Collections;
import java.util.List;

import static humanextended.HumanExtendedMod.imagePath;
import static theHuman.HumanMod.getModID;


public class Exercise extends BaseCard implements OnShuffleSubscriber {

    public static final String ID = theHuman.cards.Exercise.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(getModID() + ":LifestyleWords");
    private static final TooltipInfo toolTipInfo = new TooltipInfo("[#2000a7]" + uiStrings.TEXT[0], uiStrings.TEXT[1]);

    public Exercise() {
        super(ID, info, "theHumanResources/images/cards/Exercise.png");
        setCostUpgrade(1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(toolTipInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower health = p.getPower(HealthinessPower.POWER_ID);
        if (health != null) {
            Wiz.atb(new GainEnergyAction(health.amount / 5));
        }
    }

    @Override
    public void onShuffle() {
        Wiz.applyToSelf(new HealthinessPower(Wiz.p(), Wiz.p(), 1));
    }
}

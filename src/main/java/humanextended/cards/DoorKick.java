package humanextended.cards;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;
import theHuman.powers.TechniquePower;

import java.util.Collections;
import java.util.List;

import static humanextended.HumanExtendedMod.imagePath;
import static theHuman.HumanMod.getModID;

// renamed from Arcade
public class DoorKick extends BaseCard {

    public static final String ID = theHuman.cards.Arcade.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);


    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(getModID() + ":TechniqueWords");
    private static final TooltipInfo toolTipInfo = new TooltipInfo("[#a83277]" + uiStrings.TEXT[0], uiStrings.TEXT[1]);

    public DoorKick() {
        super(ID, info, imagePath("cards/attack/DoorKick.png"));
        setDamage(10, 5);
        setMagic(4);
        setCustomVar("HEALTH", 1, 1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(toolTipInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage);
        Wiz.atb(new ReducePowerAction(p, p, HealthinessPower.POWER_ID, magicNumber));
        if (p.hasPower(TechniquePower.POWER_ID)) {
            Wiz.applyToSelf(new HealthinessPower(p, p, customVar("HEALTH")));
        }

        Wiz.applyToSelf(new TechniquePower(p, p, 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Wiz.p().hasPower(TechniquePower.POWER_ID) ? Color.valueOf("a83277") : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}

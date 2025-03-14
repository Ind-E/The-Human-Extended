package humanextended.actions;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import basemod.BaseMod;

public class BetterDiscoverAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private Predicate<AbstractCard> filter;
    private int numChoices;

    public BetterDiscoverAction() {
        this(3, null);
    }

    public BetterDiscoverAction(int choices) {
        this(choices, null);
    }

    public BetterDiscoverAction(int choices, Predicate<AbstractCard> filter) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = startDuration;
        this.numChoices = choices;
        this.filter = filter;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards;
        generatedCards = generateCardChoices(filter);

        if (duration == startDuration) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1],
                    false);
        } else {
            if (!retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();

                    if (AbstractDungeon.player.hasPower(MasterRealityPower.POWER_ID)) {
                        disCard.upgrade();
                    }

                    disCard.current_x = -1000.0F * Settings.xScale;
                    if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        AbstractDungeon.effectList.add(
                                new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F,
                                Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                retrieveCard = true;
            }

        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices(Predicate<AbstractCard> filter) {
        ArrayList<AbstractCard> cardChoices = new ArrayList<AbstractCard>();

        while (cardChoices.size() != numChoices) {
            boolean dupe = false;
            AbstractCard tmp = null;
            if (filter == null) {
                tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
            } else {
                tmp = returnTrulyRandomFilteredCardInCombat(filter);
            }

            for (AbstractCard c : cardChoices) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                cardChoices.add(tmp.makeCopy());
            }
        }

        return cardChoices;
    }

    public static AbstractCard returnTrulyRandomFilteredCardInCombat(Predicate<AbstractCard> filter) {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();

        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (!c.hasTag(CardTags.HEALING) && filter.test(c)) {
                cards.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (!c.hasTag(CardTags.HEALING) && filter.test(c)) {
                cards.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (!c.hasTag(CardTags.HEALING) && filter.test(c)) {
                cards.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        return cards.get(AbstractDungeon.cardRandomRng.random(cards.size() - 1));
    }
}

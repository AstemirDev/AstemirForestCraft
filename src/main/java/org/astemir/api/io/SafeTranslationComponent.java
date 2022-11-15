package org.astemir.api.io;


import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.TextComponentMessageFormatHandler;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeTranslationComponent extends TextComponent implements ITargetedTextComponent {


    private static final Object[] ZERO_ARGUMENTS = new Object[0];
    private static final ITextProperties field_240754_e_ = ITextProperties.func_240652_a_("%");
    private static final ITextProperties field_240755_f_ = ITextProperties.func_240652_a_("null");
    private final Supplier<String> key;
    private final Object[] formatArgs;
    @Nullable
    private LanguageMap languageMap;
    private final List<ITextProperties> children = Lists.newArrayList();
    private static final Pattern STRING_VARIABLE_PATTERN = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public SafeTranslationComponent(Supplier<String> translationKeySupplier) {
        this.key = translationKeySupplier;
        this.formatArgs = ZERO_ARGUMENTS;
    }

    public SafeTranslationComponent(Supplier<String> translationKeySupplier, Object... args) {
        this.key = translationKeySupplier;
        this.formatArgs = args;
    }

    /**
     * Ensures that all of the children are up to date with the most recent translation mapping.
     */
    private void ensureInitialized() {
        LanguageMap languagemap = LanguageMap.getInstance();
        if (languagemap != this.languageMap) {
            this.languageMap = languagemap;
            this.children.clear();
            String s = languagemap.func_230503_a_(this.key.get());

            try {
                this.func_240758_a_(s);
            } catch (TranslationTextComponentFormatException translationtextcomponentformatexception) {
                this.children.clear();
                this.children.add(ITextProperties.func_240652_a_(s));
            }

        }
    }

    private void func_240758_a_(String p_240758_1_) {
        Matcher matcher = STRING_VARIABLE_PATTERN.matcher(p_240758_1_);

        try {
            int i = 0;

            int j;
            int l;
            for(j = 0; matcher.find(j); j = l) {
                int k = matcher.start();
                l = matcher.end();
                if (k > j) {
                    String s = p_240758_1_.substring(j, k);
                    if (s.indexOf(37) != -1) {
                        throw new IllegalArgumentException();
                    }

                    this.children.add(ITextProperties.func_240652_a_(s));
                }

                String s4 = matcher.group(2);
                String s1 = p_240758_1_.substring(k, l);
                if ("%".equals(s4) && "%%".equals(s1)) {
                    this.children.add(field_240754_e_);
                } else {
                    if (!"s".equals(s4)) {
                        throw new TranslationTextComponentFormatException(new TranslationTextComponent(key.get()), "Unsupported format: '" + s1 + "'");
                    }
                    String s2 = matcher.group(1);
                    int i1 = s2 != null ? Integer.parseInt(s2) - 1 : i++;
                    if (i1 < this.formatArgs.length) {
                        this.children.add(this.func_240757_a_(i1));
                    }
                }
            }

            if (j == 0) {
                j = TextComponentMessageFormatHandler.handle(new TranslationTextComponent(key.get()), this.children, this.formatArgs, p_240758_1_);
            }
            if (j < p_240758_1_.length()) {
                String s3 = p_240758_1_.substring(j);
                if (s3.indexOf(37) != -1) {
                    throw new IllegalArgumentException();
                }

                this.children.add(ITextProperties.func_240652_a_(s3));
            }

        } catch (IllegalArgumentException illegalargumentexception) {
            throw new TranslationTextComponentFormatException(new TranslationTextComponent(key.get()), illegalargumentexception);
        }
    }

    private ITextProperties func_240757_a_(int p_240757_1_) {
        if (p_240757_1_ >= this.formatArgs.length) {
            throw new TranslationTextComponentFormatException(new TranslationTextComponent(key.get()), p_240757_1_);
        } else {
            Object object = this.formatArgs[p_240757_1_];
            if (object instanceof ITextComponent) {
                return (ITextComponent)object;
            } else {
                return object == null ? field_240755_f_ : ITextProperties.func_240652_a_(object.toString());
            }
        }
    }

    public TranslationTextComponent copyRaw() {
        return new TranslationTextComponent(this.key.get(), this.formatArgs);
    }

    @OnlyIn(Dist.CLIENT)
    public <T> Optional<T> func_230534_b_(ITextProperties.IStyledTextAcceptor<T> acceptor, Style style) {
        this.ensureInitialized();

        for(ITextProperties itextproperties : this.children) {
            Optional<T> optional = itextproperties.getComponentWithStyle(acceptor, style);
            if (optional.isPresent()) {
                return optional;
            }
        }

        return Optional.empty();
    }

    public <T> Optional<T> func_230533_b_(ITextProperties.ITextAcceptor<T> acceptor) {
        this.ensureInitialized();

        for(ITextProperties itextproperties : this.children) {
            Optional<T> optional = itextproperties.getComponent(acceptor);
            if (optional.isPresent()) {
                return optional;
            }
        }

        return Optional.empty();
    }

    public IFormattableTextComponent func_230535_a_(@Nullable CommandSource p_230535_1_, @Nullable Entity p_230535_2_, int p_230535_3_) throws CommandSyntaxException {
        Object[] aobject = new Object[this.formatArgs.length];

        for(int i = 0; i < aobject.length; ++i) {
            Object object = this.formatArgs[i];
            if (object instanceof ITextComponent) {
                aobject[i] = TextComponentUtils.func_240645_a_(p_230535_1_, (ITextComponent)object, p_230535_2_, p_230535_3_);
            } else {
                aobject[i] = object;
            }
        }

        return new TranslationTextComponent(this.key.get(), aobject);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        } else if (!(p_equals_1_ instanceof TranslationTextComponent)) {
            return false;
        } else {
            TranslationTextComponent translationtextcomponent = (TranslationTextComponent)p_equals_1_;
            return Arrays.equals(this.formatArgs, translationtextcomponent.getFormatArgs()) && this.key.equals(translationtextcomponent.getKey()) && super.equals(p_equals_1_);
        }
    }

    public int hashCode() {
        int i = super.hashCode();
        i = 31 * i + this.key.hashCode();
        return 31 * i + Arrays.hashCode(this.formatArgs);
    }

    public String toString() {
        return "TranslatableComponent{key='" + this.key + '\'' + ", args=" + Arrays.toString(this.formatArgs) + ", siblings=" + this.siblings + ", style=" + this.getStyle() + '}';
    }

    public Supplier<String> getKeySupplier() {
        return this.key;
    }


    public Object[] getFormatArgs() {
        return this.formatArgs;
    }
}

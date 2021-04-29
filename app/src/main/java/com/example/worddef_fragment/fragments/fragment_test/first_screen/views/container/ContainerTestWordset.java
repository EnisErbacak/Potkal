package com.example.worddef_fragment.fragments.fragment_test.first_screen.views.container;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.CheckBox;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_test.first_screen.views.ChckBoxTest;
import com.example.worddef_fragment.fragments.fragment_test.first_screen.views.TvTestFirstSetName;
import com.example.worddef_fragment.fragments.fragment_wordset.views.container.ContainerInnerLft;
import com.example.worddef_fragment.fragments.fragment_wordset.views.container.ContainerInnerRght;
import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.TvWordCount;
import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.TvWordsetLeft;
import com.example.worddef_fragment.fragments.processes.explorer.FragmentExplorerFactory;
import com.example.worddef_fragment.other.PixelConverter;

import java.io.File;

public class ContainerTestWordset extends ConstraintLayout {
    // Margin values
    private final int VAL_MRGN_LFT = PixelConverter.pix2Dip(getContext(), 10);
    private final int VAL_MRGN_TOP = PixelConverter.pix2Dip(getContext(), 7);
    private final int VAL_MRGN_RGHT = PixelConverter.pix2Dip(getContext(), 10);
    private final int VAL_MRGN_BTTM = PixelConverter.pix2Dip(getContext(), 7);

    // Padding values
    private final int VAL_PAD_LFT = PixelConverter.pix2Dip(getContext(), 5);
    private final int VAL_PAD_TOP = PixelConverter.pix2Dip(getContext(), 7);
    private final int VAL_PAD_RGHT = PixelConverter.pix2Dip(getContext(), 0);
    private final int VAL_PAD_BTTM = PixelConverter.pix2Dip(getContext(), 7);

    private int COL_BG;

    private final int VAL_CRNR_RDS = PixelConverter.pix2Dip(getContext(), 15);

    private ConstraintSet constraintSet;
    private LayoutParams lp;

    private ChckBoxTest checkBox;
    private TvTestFirstSetName txtViewWrdSet;
    private ContainerInnerRght containerInnerRght;
    private ContainerInnerLft containerInnerLft;
    private String setName;
    private int countWord;

    public ContainerTestWordset(final Context context, final String setName) {
        super(context);
        this.setName = setName;
        onCreate(context);
    }

    private void onCreate(Context context) {
        this.setId(ConstraintLayout.generateViewId());
        COL_BG = Integer.parseInt(new SPEditor().getValue(context, SPEditor.COL_WORDSET));

        this.constraintSet = new ConstraintSet();
        txtViewWrdSet = new TvTestFirstSetName(getContext(), setName);

        checkBox=new ChckBoxTest(context);
        containerInnerLft = new ContainerInnerLft(getContext(), new View[] {checkBox, txtViewWrdSet});
        countWord = new FragmentExplorerFactory().create("worddef").getCount(new PathPickerFactory().create("wordset").get(getContext()) + File.separator + setName);


        containerInnerRght = new ContainerInnerRght(getContext(), new View[]{new TvWordCount(getContext(), countWord) });
        setStyle();
    }

    public void setStyle() {
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setLayoutParams(lp);
        //setMargin(lp);
        //setPad();

        //setBgShape(getGradientDrawable(COL_BG, VAL_CRNR_RDS));
        locateSubPanels();
    }

    private void locateSubPanels() {
        this.addView(containerInnerLft);
        this.addView(containerInnerRght);
        constraintSet.clone(this);

        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.START, this.getId(), ConstraintSet.START);
        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP);

        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.END, this.getId(), ConstraintSet.END, 0);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, 0);


        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.END, containerInnerRght.getId(), ConstraintSet.START);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.START, containerInnerLft.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }


    private Drawable getGradientDrawable(int color, int radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    private void setBgShape(Drawable shape) {
        setBackground(shape);
    }


    private void setMargin(LayoutParams lp) {
        lp.setMargins(VAL_MRGN_LFT, VAL_MRGN_TOP, VAL_MRGN_RGHT, VAL_MRGN_BTTM);
    }

    private void setPad() {
        setPadding(VAL_PAD_LFT, VAL_PAD_TOP, VAL_PAD_RGHT, VAL_PAD_BTTM);
    }

    public ContainerInnerRght getContainerInnerRght() {
        return containerInnerRght;
    }

    public ContainerInnerLft getContainerInnerLft() {
        return containerInnerLft;
    }

    public String getSetName() {
        return setName;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}

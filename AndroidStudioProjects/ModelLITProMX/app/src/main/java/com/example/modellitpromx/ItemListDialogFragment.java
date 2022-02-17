package com.example.modellitpromx;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_OPTIONS_ARRAY = "options_array";

    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(ArrayList<String> optionsStringArray) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_OPTIONS_ARRAY, optionsStringArray);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getStringArrayList(ARG_OPTIONS_ARRAY)));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView optionsText;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            optionsText = itemView.findViewById(R.id.optionText);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final ArrayList<String> mOptionsStringArray;
        /*TODO: The array has to be passed into the constructor when the button is clicked
                so that we have access to both the item count and the strings that may vary
                4/30/21
         */

        ItemAdapter(ArrayList<String> optionsStringArray) {
            mOptionsStringArray = optionsStringArray;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.optionsText.setText(mOptionsStringArray.get(position));

            switch (mOptionsStringArray.get(position)) {
                case "Cancel":
                case "Clear Filter":
                case "Show Hidden Sessions":
                case "Hide Hidden Sessions": {
                    holder.optionsText.setTextColor(getResources().getColor(R.color.info_blue));
                    break;
                }
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mOptionsStringArray.size();
        }

    }

}
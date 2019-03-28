package twelsch.htl.pos3.demo.android.firestore;

import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter {
    private Context mContext;
    private List<Note> notelist = new ArrayList<>();

    public Adapter(@NonNull Context context, ArrayList<Note> list) {
        super(context, 0 , list);
        mContext = context;
        notelist= list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.message,parent,false);

        Note currentNote = notelist.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView);
        name.setText(currentNote.getEmail());


        TextView mes = (TextView) listItem.findViewById(R.id.textView2);
        mes.setText(currentNote.getMessage());

        TextView date = (TextView) listItem.findViewById(R.id.textView3);
        date.setText(currentNote.myString());

        return listItem;
    }
}


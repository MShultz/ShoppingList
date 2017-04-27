package shultz.mary.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ListView shoppingList;
    private EditText newItemText;
    private Button submitButton;
    private ArrayAdapter<String> listAdapter;
    private DatabaseHandler listDatabase;
    private ArrayList<ShoppingItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewObjects();
        initializeDatabase();
        initializeAdapter();
        initializeList();

    }

    private void initializeDatabase() {
        listDatabase = new DatabaseHandler(this.openOrCreateDatabase(DatabaseHandler.DB_NAME, MODE_PRIVATE, null));
    }

    private void initializeAdapter() {
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        shoppingList.setAdapter(listAdapter);
    }

    private void initializeViewObjects() {
        shoppingList = (ListView) findViewById(R.id.shoppingList);
        newItemText = (EditText) findViewById(R.id.newItemText);
        submitButton = (Button) findViewById(R.id.addButton);
    }

    private void initializeList() {
        items = listDatabase.getAllItems();
        listAdapter.addAll(getItemsAsStrings());
        listAdapter.notifyDataSetChanged();
    }

    public void onAddition(View view) {
        if(!newItemText.getText().toString().equals("")) {
            ShoppingItem item = new ShoppingItem(newItemText.getText().toString(), DATE_FORMAT.format(new Date()), null);
            updateAdapter(item);
            listDatabase.addItem(item);
            items.add(item);
        }else{
            Toast.makeText(this, "No item to add", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateAdapter(ShoppingItem item){
        listAdapter.add(item.toString());
        listAdapter.notifyDataSetChanged();
        newItemText.setText("");
    }

    private ArrayList<String> getItemsAsStrings(){
        ArrayList<String> itemsAsString = new ArrayList<>();
        for(ShoppingItem item: items){
            itemsAsString.add(item.toString());
        }
        return itemsAsString;
    }
}
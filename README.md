LIST FRAGMENT

- thông thường để hiển thị 1 danh sách ta có thể sử dụng ListView
- trong Fragment ta cũng có thể sử dụng ListView
- nhưng Fragment có hỗ trợ 1 dạng đặc biệt mà ta không cần sử dụng ListView mà trực tiếp sử dụng Fragment làm 1 danh sách
- cách sử dụng cũng tương tự như bộ 3 ArrayList, Adapter, ListView, nhưng bây giờ ta sẽ thay ListView bằng 1 Fragment để thực hiện nhiệm vụ hiển thị ArrayList
___

LAYOUT LIST FRAGMENT

- thiết kế layout cho ListFragment
	- LinearLayout
	- vertical
- __fragment_citylist.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

- trong layout của ListFragment ta vẫn khai báo 1 ListView, nhưng vì ta sẽ sử dụng cách đặc biệt lấy Fragment này làm ListView, nên __id__ của ListView khai báo trong Fragment sẽ sử dụng id sẵn có của Android dùng để đặt id cho Fragment làm ListView là: __@android:id/list__ 

```xml
    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

___

CODE LIST FRAGMENT

- vì ta sẽ sử dụng cách đặc biệt biến Fragment thành 1 ListView, nên Fragment dùng để hiển thị danh sách sẽ __extends ListFragment__
- override Method __onCreateView()__ ta vẫn return 1 View thông qua inflater như những phần trước
- __CityListFragment.java__
```java
package com.hienqp.listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CityListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_citylist, container, false);
    }
}
```
- vì bản thân CityListFragment đã là 1 ListView nên ta không cần phải khai báo ánh xạ ListView
- nhưng data và adapter ta vẫn khai báo ánh xạ bình thường, chỉ thay đổi một số điểm cho phù hợp với trường hợp đặc biệt
- ta sẽ khai báo 1 data, ở đây ta khai báo 1 mảng String chứa tên các thành phố
```
    String[] cityList = {"Hà Nội","Hải Phòng","Nha Trang","Khánh Hòa","Hồ Chí Minh","Cần Thơ"};
```
- 1 ArrayAdapter
```
    ArrayAdapter adapter;
```
- mảng data đã có, ListView thì không cần khai báo ánh xạ, ta chỉ khởi tạo ArrayAdapter trong onCreateView()
```
adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cityList);
```
- có 3 tham số
	- Context: hiểu đơn giản là màn hình hiển thị, vì Fragment cũng chỉ là 1 View trong Activity, vì vậy ta phải sử dụng __getActivity()__
	- mẫu layout dùng để hiển thị data
	- data: mảng String đã khai báo
- vì không có ListView để gọi setAdapter(), vì vậy ta sẽ sử dụng Method __setListAdapter()__ của __ListFragment__ ta đã extends
```
setListAdapter(adapter);
```
- như vậy ta có __CityListFragment__ hoàn chỉnh như sau
```java
package com.hienqp.listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CityListFragment extends ListFragment {
    String[] cityList = {"Hà Nội","Hải Phòng","Nha Trang","Khánh Hòa","Hồ Chí Minh","Cần Thơ"};
    ArrayAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cityList);
        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_citylist, container, false);
    }
}
```

- như vậy, khi CityListFragment được gọi, nó sẽ hiển thị 1 danh sách các thành phố, để gọi được CityListFragment ta phải cài đặt trong Activity sẽ gọi nó, ở đây là MainActivity

___

LAYOUT ACTIVITY MAIN

- vì phần này đơn giản là ta thực hiện việc hiển thị được 1 ListFragment lên 1 Activity, nên ta sẽ khai báo 1 Static Fragment trong Layout
- cần lưu ý: 2 thuộc tính quan trọng cho thẻ fragment chính là __id__ và __name__, những thứ còn lại có thể dùng Design để setup cho Layout Activity Main
- __activity_main.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.116"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.035" />

    <fragment
        android:id="@+id/fragment_content"
        android:name="com.hienqp.listfragment.CityListFragment"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
- khi build app ta sẽ được như sau
<img src="https://github.com/hienqp/ngay044-Fragment-ListFragment/blob/main/Screenshot%202022-09-23%20235512.png">


package ms.sapientia.minecraftservertrackerapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class FragmentNavigation /*extends Fragment*/ {
    private static final String TAG = FragmentNavigation.class.getSimpleName();

    private static FragmentNavigation sInstance;
    private static FragmentManager mFragmentManager;
    private static FragmentTransaction mFragmentTransaction;

    public static FragmentNavigation getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new FragmentNavigation();
            mFragmentManager = ((MainActivity) context).getSupportFragmentManager();
        }

        return sInstance;
    }

    /**
     * Loads the fragment into the container.
     *
     * @param container      on which will be the fragment displayed
     * @param fragmentToShow fragment to be loaded
     */
    public void showFragment(int container, Fragment fragmentToShow) {
        replaceFragment(fragmentToShow, container);
    }

    /**
     * Loads the fragment into the container.
     *
     * @param containerView  on which will be the fragment displayed
     * @param fragmentToShow ragment to be loaded
     */
    public void showFragment(View containerView, Fragment fragmentToShow) {
        replaceFragment(fragmentToShow, containerView.getId());
    }

    public void popBackstack() {
        mFragmentManager.popBackStack();
    }

    /**
     * This method adds a new fragment on top of the stack.
     *
     * @param fragment new fragment
     */
    private void addFragment(Fragment fragment, int container) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(container, fragment, fragment.getTag());
        try {
            mFragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes the fragment from fragment stack.
     *
     * @param fragment fragment which should be removed
     */
    private void removeFragment(Fragment fragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.remove(fragment);
        mFragmentTransaction.commit();
    }

    /**
     * This method replaces the fragment on top of the stack.
     *
     * @param fragment new fragment
     */
    private void replaceFragment(Fragment fragment, int container) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        Fragment topFragment = mFragmentManager.findFragmentById(container);
        if (topFragment == null) {
            // if there is nothing to replace, then add a new one:
            addFragment(fragment, container);
        } else {
            // if there is fragment to replace, then replace it:
            mFragmentTransaction.replace(container, fragment, fragment.getTag());
            mFragmentTransaction.addToBackStack(fragment.getTag());
            try {
                mFragmentTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //mFragmentManager.executePendingTransactions();
        }
    }

    /**
     * This method returns the current fragment.
     *
     * @return current fragment.
     */
    private Fragment getCurrentFragment(int container) {
        return mFragmentManager.findFragmentById(container);
    }

    public void onBackPressed(MainActivity activity/*, View containerView*/) {

        //// If Home page is open: double press exit:
        //if( getCurrentFragment(R.id.main_fragment_container) instanceof HomeFragment
        //    && getCurrentFragment(R.id.home_bottom_fragment_container) instanceof GenreListFragment ) {
        //        popBackstack();
        //    return;
        //}

        // Send back press for individual fragments

        activity.onBackPressed();
    }
}

package com.example.project.view.contact_list;

import com.example.project.view.contact_details.DetailActivityIntent;
import com.example.project.view.contact_details.DetailFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContactListActivityTest {

    public static final int CONTACT_ID = 42;

    @Mock
    DetailActivityIntent detailActivityIntent;

    @Mock
    ContactListFragment contactListFragment;

    @Mock
    DetailFragment detailFragment;

    @InjectMocks
    ContactListActivity contactListActivity;

    @Test
    public void testOnShowContact_portrait() throws Exception {
        givenPortraitMode();
        contactListActivity.onShowContact(42);
        thenDetailAreShownInNewActivity(CONTACT_ID);
    }

    @Test
    public void testOnShowContact_landscape() throws Exception {
        givenLandscapeMode();
        contactListActivity.onShowContact(CONTACT_ID);
        thenDetailsAreShownInDetailFragment(CONTACT_ID);
    }

    private void thenDetailAreShownInNewActivity(long expectedContactId) {
        verify(detailActivityIntent).start(expectedContactId);
    }

    private void thenDetailsAreShownInDetailFragment(long expectedContactId) {
        verify(detailFragment).onShowContact(expectedContactId);
    }

    private void givenPortraitMode() {
        contactListActivity.detailFragment = null;
    }

    private void givenLandscapeMode() {
        assertThat(contactListActivity.detailFragment).isNotNull();
    }
}
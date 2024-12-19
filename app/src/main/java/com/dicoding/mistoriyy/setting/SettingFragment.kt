package com.dicoding.mistoriyy.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.*
import com.dicoding.mistoriyy.R
import com.dicoding.mistoriyy.databinding.FragmentNotificationsBinding
import com.dicoding.mistoriyy.welcome.WelcomeActivity

class SettingFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val SettingViewModel: SettingViewModel by viewModels {
        SettingViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SettingViewModel.userName.observe(viewLifecycleOwner) { userName ->
            binding.textView2.text = userName
        }

        binding.btnLogout.setOnClickListener {
            dialogConfirmationLogout()
        }

        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

    }

    private fun dialogConfirmationLogout() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.logout_title))
            setMessage(getString(R.string.logout_confirmation))
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                SettingViewModel.logout()
                val intent = Intent(requireContext(), WelcomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
            }
            setNegativeButton(getString(R.string.no), null)
            create()
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}